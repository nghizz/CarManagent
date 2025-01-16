package dao;

import entity.User;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Method to authenticate a user based on username and password using raw SQL.
     *
     * @param username the username provided by the user.
     * @param password the password provided by the user.
     * @return User object if login is successful, otherwise null.
     */
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        Connection connection = null;
        try {
            connection = DBUtils.getConnection(); // Get the JDBC connection
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Create and return a User object if credentials are valid
                User user = new User();
                user.setUserId(resultSet.getInt("userId")); // Assuming userId is an integer
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setFullName(resultSet.getString("fullName"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setIdentityCardNumber(resultSet.getString("identityCardNumber"));
                user.setCreatedAt(resultSet.getTimestamp("createdAt").toLocalDateTime());
                user.setUpdatedAt(resultSet.getTimestamp("updatedAt").toLocalDateTime());
                return user;
            }
            return null; // Return null if no matching user found
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.closeConnection(connection); // Close the connection
        }
    }
    
}
