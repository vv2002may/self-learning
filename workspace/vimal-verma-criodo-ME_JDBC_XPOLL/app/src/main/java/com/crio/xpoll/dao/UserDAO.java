package com.crio.xpoll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.crio.xpoll.model.User;
import com.crio.xpoll.util.DatabaseConnection;
import com.mysql.cj.xdevapi.Result;

/**
 * Data Access Object (DAO) for managing users in the XPoll application. Provides methods for
 * creating and retrieving user information.
 */
public class UserDAO {

    private final DatabaseConnection databaseConnection;
    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet rs = null;

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
        try {
            myConn = databaseConnection.getConnection();
            myStmt = myConn
                    .prepareStatement("INSERT INTO users (username, password) VALUES (?, ?);");
            myStmt.setString(1, username);
            myStmt.setString(2, password);

            int myRes = myStmt.executeUpdate();
            if (myRes == 0) {
                return null;
            }

            // Retrieve the newly created user
            myStmt = myConn
                    .prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?;");
            myStmt.setString(1, username);
            myStmt.setString(2, password);

            rs = myStmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                return new User(userId, username, password);
            } else {
                // user not found
                return null;
            }
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
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A User object representing the user with the specified ID, or null if no user is found.
     * @throws SQLException If a database error occurs during the retrieval.
     */
    public User getUserById(int userId) throws SQLException {
        try{
            myConn=databaseConnection.getConnection();
            myStmt=myConn.prepareStatement("select * from users where id=?;");
            myStmt.setInt(1, userId);

            rs=myStmt.executeQuery();
            if(rs.next()){
                String username=rs.getString("username");
                String password=rs.getString("password");
                return new User(userId, username, password);
            }
            else{
                return null;
            }
        }
        finally{
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
