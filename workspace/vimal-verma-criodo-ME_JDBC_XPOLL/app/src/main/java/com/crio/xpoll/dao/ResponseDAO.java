package com.crio.xpoll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crio.xpoll.model.Response;
import com.crio.xpoll.util.DatabaseConnection;

/**
 * Data Access Object (DAO) for managing responses in the XPoll application. Provides methods for
 * creating responses to polls.
 */
public class ResponseDAO {
    private final DatabaseConnection databaseConnection;
    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet rs = null;

    /**
     * Constructs a ResponseDAO with the specified DatabaseConnection.
     *
     * @param databaseConnection The DatabaseConnection to be used for database operations.
     */
    public ResponseDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Creates a new response for a specified poll, choice, and user.
     *
     * @param pollId The ID of the poll to which the response is made.
     * @param choiceId The ID of the choice selected by the user.
     * @param userId The ID of the user making the response.
     * @return A Response object representing the created response.
     * @throws SQLException If a database error occurs during response creation.
     */
    public Response createResponse(int pollId, int choiceId, int userId) throws SQLException {

        try {
            myConn = databaseConnection.getConnection();
            myStmt = myConn.prepareStatement("Insert into responses (poll_id, choice_id, user_id) values (?,?,?)");
            myStmt.setLong(1, pollId);
            myStmt.setLong(2, choiceId);
            myStmt.setLong(3, userId);

            int res = myStmt.executeUpdate();
            if (res == 0) {
                return null;
            }
            Response response = new Response(pollId, choiceId, userId);
            return response;

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
}
