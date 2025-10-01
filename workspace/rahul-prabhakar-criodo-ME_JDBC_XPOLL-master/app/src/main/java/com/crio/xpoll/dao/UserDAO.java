package com.crio.xpoll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crio.xpoll.model.User;
import com.crio.xpoll.util.DatabaseConnection;

/**
 * Data Access Object (DAO) for managing users in the XPoll application.
 * Provides methods for creating and retrieving user information.
 */
public class UserDAO {

    private final DatabaseConnection databaseConnection;

    /**
     * Constructs a UserDAO with the specified DatabaseConnection.
     *
     * @param databaseConnection The DatabaseConnection to be used for database operations.
     */
    public UserDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Creates a new user with the specified username and password.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @return A User object representing the created user.
     * @throws SQLException If a database error occurs during user creation.
     */
    public User createUser(String username, String password) throws SQLException {

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set the values for the query
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Retrieve the auto-generated ID
                        int userId = generatedKeys.getInt(1);
                        return new User(userId, username, password);
                    }
                }
            }
        }
        return null; // Return null if user creation fails
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A User object representing the user with the specified ID, or null if no user is found.
     * @throws SQLException If a database error occurs during the retrieval.
     */
    public User getUserById(int userId) throws SQLException {

        String query = "SELECT id, username, password FROM users WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Set the value for the query
            statement.setInt(1, userId);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve user details
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    return new User(userId, username, password);
                }
            }
        }


        return null; // Return null if no user is found
    }
}