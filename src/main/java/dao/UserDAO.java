package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDAO {

    // Kết nối đến cơ sở dữ liệu
	public static Connection getConnection() throws SQLException {
	    Connection conn = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/carmanagement";  // Đảm bảo database và cổng chính xác
	        String username = "root";  // Tên người dùng MySQL
	        String password = "";  // Mật khẩu người dùng MySQL
	        conn = DriverManager.getConnection(url, username, password);
	        if (conn == null) {
	            throw new SQLException("Failed to establish connection");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new SQLException("Error connecting to the database: " + e.getMessage());
	    }
	    return conn;
	}


    // Thêm người dùng mới vào cơ sở dữ liệu
    public boolean addUser(User user) {
        String query = "INSERT INTO users (username, password, role, fullName, phoneNumber, identityCardNumber, createdAt, updatedAt) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getFullName());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getIdentityCardNumber());
            stmt.setObject(7, user.getCreatedAt());
            stmt.setObject(8, user.getUpdatedAt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy người dùng theo username
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong("userId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setFullName(rs.getString("fullName"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setIdentityCardNumber(rs.getString("identityCardNumber"));
                user.setCreatedAt(rs.getObject("createdAt", java.time.LocalDateTime.class));
                user.setUpdatedAt(rs.getObject("updatedAt", java.time.LocalDateTime.class));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cập nhật thông tin người dùng
    public boolean updateUser(User user) {
        String query = "UPDATE users SET password = ?, role = ?, fullName = ?, phoneNumber = ?, identityCardNumber = ?, updatedAt = ? "
                     + "WHERE userId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getRole());
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getIdentityCardNumber());
            stmt.setObject(6, user.getUpdatedAt());
            stmt.setLong(7, user.getUserId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa người dùng
    public boolean deleteUser(Long userId) {
        String query = "DELETE FROM Users WHERE userId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
