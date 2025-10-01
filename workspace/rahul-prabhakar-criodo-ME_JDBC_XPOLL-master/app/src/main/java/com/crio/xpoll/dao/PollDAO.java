package com.crio.xpoll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crio.xpoll.model.Choice;
import com.crio.xpoll.model.Poll;
import com.crio.xpoll.model.PollSummary;
import com.crio.xpoll.util.DatabaseConnection;

/**
 * Data Access Object (DAO) for managing polls in the XPoll application.
 * Provides methods for creating, retrieving, closing polls, and fetching poll
 * summaries.
 */
public class PollDAO {

    private final DatabaseConnection databaseConnection;

    /**
     * Constructs a PollDAO with the specified DatabaseConnection.
     *
     * @param databaseConnection The DatabaseConnection to be used for database
     *                           operations.
     */
    public PollDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Creates a new poll with the specified question and choices.
     *
     * @param userId   The ID of the user creating the poll.
     * @param question The question for the poll.
     * @param choices  A list of choices for the poll.
     * @return The created Poll object with its associated choices.
     * @throws SQLException If a database error occurs during poll creation.
     */
    public Poll createPoll(int userId, String question, List<String> choices) throws SQLException {
        String insertPollQuery = "INSERT INTO polls (user_id, question, is_closed) VALUES (?, ?, ?)";
        String insertChoiceQuery = "INSERT INTO choices (poll_id, choice_text) VALUES (?, ?)";

        try (Connection conn = databaseConnection.getConnection();
                PreparedStatement pollStatement = conn.prepareStatement(insertPollQuery,
                        Statement.RETURN_GENERATED_KEYS)) {

            // Insert poll
            pollStatement.setInt(1, userId);
            pollStatement.setString(2, question);
            pollStatement.setBoolean(3, false); // isClosed is set to false for a new poll
            pollStatement.executeUpdate();

            // Get the generated poll ID
            try (ResultSet generatedKeys = pollStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int pollId = generatedKeys.getInt(1);

                    // Insert choices for the poll
                    try (PreparedStatement choiceStatement = conn.prepareStatement(insertChoiceQuery)) {
                        for (String choice : choices) {
                            choiceStatement.setInt(1, pollId);
                            choiceStatement.setString(2, choice);
                            choiceStatement.addBatch();
                        }
                        choiceStatement.executeBatch();
                    }

                    // Retrieve the choices to construct the Poll object
                    List<Choice> choiceList = new ArrayList<>();
                    String selectChoicesSQL = "SELECT id, choice_text FROM choices WHERE poll_id = ?";
                    try (PreparedStatement selectChoicesStatement = conn.prepareStatement(selectChoicesSQL)) {
                        selectChoicesStatement.setInt(1, pollId);
                        try (ResultSet resultSet = selectChoicesStatement.executeQuery()) {
                            while (resultSet.next()) {
                                int choiceId = resultSet.getInt("id");
                                String choiceText = resultSet.getString("choice_text");
                                choiceList.add(new Choice(choiceId, pollId, choiceText));
                            }
                        }
                    }

                    // Return the created poll
                    return new Poll(pollId, userId, question, choiceList, false);

                }
            }
        }
        return null; // Return null if poll creation fails
    }

    /**
     * Retrieves a poll by its ID.
     *
     * @param pollId The ID of the poll to retrieve.
     * @return The Poll object with its associated choices.
     * @throws SQLException If a database error occurs or the poll is not found.
     */
    public Poll getPoll(int pollId) throws SQLException {
        String pollQuery = "SELECT id, user_id, question, is_closed FROM polls WHERE id = ?";
        String choicesQuery = "SELECT id, choice_text FROM choices WHERE poll_id = ?";

        try (Connection conn = databaseConnection.getConnection();
                PreparedStatement pollStatement = conn.prepareStatement(pollQuery);
                PreparedStatement choicesStatement = conn.prepareStatement(choicesQuery)) {

            // Get the poll
            pollStatement.setInt(1, pollId);
            try (ResultSet pollResult = pollStatement.executeQuery()) {
                if (pollResult.next()) {
                    int userId = pollResult.getInt("user_id");
                    String question = pollResult.getString("question");
                    boolean isClosed = pollResult.getBoolean("is_closed");

                    // Get the choices
                    choicesStatement.setInt(1, pollId);
                    List<Choice> choices = new ArrayList<>();
                    try (ResultSet choicesResult = choicesStatement.executeQuery()) {
                        while (choicesResult.next()) {
                            int choiceId = choicesResult.getInt("id");
                            String choiceText = choicesResult.getString("choice_text");
                            choices.add(new Choice(choiceId, pollId, choiceText));
                        }
                    }

                    // Use the existing Poll constructor that accepts 5 parameters
                    return new Poll(pollId, userId, question, choices, isClosed);
                }
            }
        }
        return null; // Return null if no poll is found
    }

    /**
     * Closes a poll by updating its status in the database.
     *
     * @param pollId The ID of the poll to close.
     * @throws SQLException If a database error occurs during the update.
     */
    public void closePoll(int pollId) throws SQLException {
        String closePollQuery = "UPDATE polls SET is_closed = ? WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(closePollQuery)) {

            statement.setBoolean(1, true); // Set isClosed to true
            statement.setInt(2, pollId);
            statement.executeUpdate();
        }
    }

    /**
     * Retrieves a list of poll summaries for the specified poll.
     *
     * @param pollId The ID of the poll for which to retrieve summaries.
     * @return A list of PollSummary objects containing the poll question, choice
     *         text, and response count.
     * @throws SQLException If a database error occurs during the query.
     */
    public List<PollSummary> getPollSummaries(int pollId) throws SQLException {
        // Query to retrieve the poll question and choices with response count
        String summaryQuery = "SELECT p.question, c.choice_text, COUNT(r.poll_id) AS response_count " +
                "FROM polls p " +
                "JOIN choices c ON p.id = c.poll_id " +
                "LEFT JOIN responses r ON c.id = r.choice_id " +
                "WHERE p.id = ? " +
                "GROUP BY p.question, c.choice_text";

        List<PollSummary> summaries = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(summaryQuery)) {

            // Set the poll ID parameter
            statement.setInt(1, pollId);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Retrieve the poll question, choice text, and response count from the result
                    // set
                    String question = resultSet.getString("question");
                    String choiceText = resultSet.getString("choice_text");
                    int responseCount = resultSet.getInt("response_count");

                    // Add a new PollSummary object to the list
                    summaries.add(new PollSummary(question, choiceText, responseCount));
                }
            }
        }

        return summaries;
    }
}