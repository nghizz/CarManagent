package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import utils.DBUtils;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO() {
        this.conn = DBUtils.getConnection();
    }

    public List<User> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM users WHERE role = 'customer'";
        List<User> customers = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User customer = new User();
                customer.setUserId(rs.getInt("userId"));
                customer.setUsername(rs.getString("username"));
                customer.setFullName(rs.getString("fullName"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setIdentityCardNumber(rs.getString("identityCardNumber"));
                customer.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                customers.add(customer);
            }
        }
        return customers;
    }

    public void addCustomer(User customer) throws SQLException {
        String query = "INSERT INTO users (username, password, role, fullName, phoneNumber, identityCardNumber, createdAt, updatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, "1");
            stmt.setString(3, "customer");
            stmt.setString(4, customer.getFullName());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setString(6, customer.getIdentityCardNumber());
            stmt.setTimestamp(7, Timestamp.valueOf(customer.getCreatedAt()));
            stmt.setTimestamp(8, Timestamp.valueOf(customer.getUpdatedAt()));
            stmt.executeUpdate();
        }
    }

    public void updateCustomer(User customer) throws SQLException {
        String query = "UPDATE users SET fullName = ?, phoneNumber = ?, identityCardNumber = ?, updatedAt = ? WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(3, customer.getIdentityCardNumber());
            stmt.setTimestamp(4, Timestamp.valueOf(customer.getUpdatedAt()));
            stmt.setInt(5, customer.getUserId());
            stmt.executeUpdate();
        }
    }

    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM users WHERE userId = ? AND role = 'customer'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
    }

    public List<User> searchCustomers(String keyword) throws SQLException {
        String query = "SELECT * FROM users WHERE role = 'customer' AND (fullName LIKE ? OR phoneNumber LIKE ? OR username LIKE ?)";
        List<User> customers = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User customer = new User();
                customer.setUserId(rs.getInt("userId"));
                customer.setUsername(rs.getString("username"));
                customer.setFullName(rs.getString("fullName"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setIdentityCardNumber(rs.getString("identityCardNumber"));
                customer.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                customers.add(customer);
            }
        }
        return customers;
    }

    public User getCustomerById(int customerId) {
        String query = "SELECT * FROM users WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("fullName"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setIdentityCardNumber(rs.getString("identityCardNumber"));
                user.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                user.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
