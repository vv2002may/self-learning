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
 * Data Access Object (DAO) for managing polls in the XPoll application. Provides methods for
 * creating, retrieving, closing polls, and fetching poll summaries.
 */
public class PollDAO {

    private final DatabaseConnection databaseConnection;
    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet rs = null;

    /**
     * Constructs a PollDAO with the specified DatabaseConnection.
     *
     * @param databaseConnection The DatabaseConnection to be used for database operations.
     */
    public PollDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Creates a new poll with the specified question and choices.
     *
     * @param userId The ID of the user creating the poll.
     * @param question The question for the poll.
     * @param choices A list of choices for the poll.
     * @return The created Poll object with its associated choices.
     * @throws SQLException If a database error occurs during poll creation.
     */
    public Poll createPoll(int userId, String question, List<String> choices) throws SQLException {

        try {
            myConn = databaseConnection.getConnection();
            myStmt = myConn
                    .prepareStatement("INSERT INTO polls (user_id, question) VALUES (?, ?);");
            myStmt.setLong(1, userId);
            myStmt.setString(2, question);

            int res = myStmt.executeUpdate();
            if (res == 0) {
                return null;
            }

            myStmt = myConn.prepareStatement("select * from polls where user_id=? AND question=?;");
            myStmt.setLong(1, userId);
            myStmt.setString(2, question);

            rs = myStmt.executeQuery();
            if (rs.next()) {
                int pollId = rs.getInt("id");
                List<Choice> choicesList = new ArrayList<>();

                for (String choiceText : choices) {
                    myStmt = myConn.prepareStatement(
                            "Insert into choices (poll_id, choice_text) values (?,?)");
                    myStmt.setLong(1, pollId);
                    myStmt.setString(2, choiceText);

                    res = myStmt.executeUpdate();
                    if (res == 0) {
                        return null;
                    }
                    myStmt = myConn.prepareStatement(
                            "select * from choices where poll_id=? AND choice_text=?;");
                    myStmt.setLong(1, pollId);
                    myStmt.setString(2, choiceText);

                    rs = myStmt.executeQuery();
                    if (rs.next()) {
                        int choiceId = rs.getInt("id");
                        Choice choice = new Choice(choiceId, pollId, choiceText);
                        choicesList.add(choice);
                    }
                }
                Poll poll = new Poll(pollId, userId, question, choicesList);
                return poll;
            }

            return null;
        } finally {
            // Close resources even if an exception occurs
            if (rs != null) {
                rs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
    }

    /**
     * Retrieves a poll by its ID.
     *
     * @param pollId The ID of the poll to retrieve.
     * @return The Poll object with its associated choices.
     * @throws SQLException If a database error occurs or the poll is not found.
     */
    public Poll getPoll(int pollId) throws SQLException {
        try {
            myConn = databaseConnection.getConnection();

            myStmt = myConn.prepareStatement("select * from polls where id=?;");
            myStmt.setLong(1, pollId);

            rs = myStmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String question = rs.getString("question");
                Boolean isClosed = rs.getBoolean("is_closed");

                List<Choice> choicesList = new ArrayList<>();

                myStmt = myConn.prepareStatement("select * from choices where poll_id=?;");
                myStmt.setLong(1, pollId);

                rs = myStmt.executeQuery();
                while (rs.next()) {
                    int choiceId=rs.getInt("id");
                    String choiceText = rs.getString("choice_text");
                    Choice choice = new Choice(choiceId, pollId, choiceText);
                    choicesList.add(choice);
                }
                Poll poll = new Poll(pollId, userId, question, choicesList,isClosed);
                return poll;
            }

            return null;
        } finally {
            // Close resources even if an exception occurs
            if (rs != null) {
                rs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
    }

    /**
     * Closes a poll by updating its status in the database.
     *
     * @param pollId The ID of the poll to close.
     * @throws SQLException If a database error occurs during the update.
     */
    public void closePoll(int pollId) throws SQLException {
        try {
            myConn = databaseConnection.getConnection();
            myStmt = myConn.prepareStatement("update polls set is_closed=TRUE where id=?;");
            myStmt.setLong(1, pollId);
            myStmt.executeUpdate();
        } finally {
            // Close resources even if an exception occurs
            if (rs != null) {
                rs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
    }

    /**
     * Retrieves a list of poll summaries for the specified poll.
     *
     * @param pollId The ID of the poll for which to retrieve summaries.
     * @return A list of PollSummary objects containing the poll question, choice text, and response
     *         count.
     * @throws SQLException If a database error occurs during the query.
     */
    public List<PollSummary> getPollSummaries(int pollId) throws SQLException {
        // List<PollSummary> pollSummaries = new ArrayList<>();
        // try {
        //     myConn = databaseConnection.getConnection();
        //     myStmt = myConn.prepareStatement("select * from polls where id=?;");
        //     myStmt.setInt(1, pollId);
        //     rs = myStmt.executeQuery();
        //     if (rs.next()) {
        //         String question = rs.getString("question");
        //         myStmt = myConn.prepareStatement("select * from choices where poll_id=?;");
        //         myStmt.setInt(1, pollId);
        //         rs = myStmt.executeQuery();
        //         while (rs.next()) {
        //             String choiceText = rs.getString("choice_text");
        //             myStmt = myConn.prepareStatement("select * from responses where poll_id=?;");
        //             myStmt.setInt(1, pollId);
        //             ResultSet rs2 = myStmt.executeQuery();
        //             if (rs2.next()) {
        //                 int responseCount = rs2.getInt(1);
        //                 System.out.println(responseCount);
        //                 PollSummary pollSummary =
        //                         new PollSummary(question, choiceText, responseCount);
        //                 pollSummaries.add(pollSummary);
        //             }
        //             if (rs2 != null) {
        //                 rs2.close();
        //             }
        //         }
        //     }
        // }

        // finally {
        //     // Close resources even if an exception occurs
        //     if (rs != null) {
        //         rs.close();
        //     }
        //     if (myStmt != null) {
        //         myStmt.close();
        //     }
        //     if (myConn != null) {
        //         myConn.close();
        //     }
        // }
        // return pollSummaries;


        String summaryQuery = "SELECT p.question, c.choice_text, COUNT(r.poll_id) AS response_count " +
                          "FROM polls p " +
                          "JOIN choices c ON p.id = c.poll_id " +
                          "LEFT JOIN responses r ON c.id = r.choice_id " +
                          "WHERE p.id = ? " +
                          "GROUP BY p.question, c.choice_text";

        // String summaryQuery ="seelct * from PollSummary where id=?";
    List<PollSummary> summaries = new ArrayList<>();

    try (Connection conn = databaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(summaryQuery)) {

        // Set the poll ID parameter
        statement.setInt(1, pollId);

        // Execute the query
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve the poll question, choice text, and response count from the result set
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
