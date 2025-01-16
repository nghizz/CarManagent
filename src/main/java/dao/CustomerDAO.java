package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO() {
        this.conn = conn;
    }

    public List<User> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM users WHERE role = 'customer'";
        List<User> customers = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User customer = new User();
                customer.setUserId(rs.getInt("user_id"));
                customer.setUsername(rs.getString("username"));
                customer.setFullName(rs.getString("full_name"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setIdentityCardNumber(rs.getString("identity_card_number"));
                customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                customers.add(customer);
            }
        }
        return customers;
    }

    public void addCustomer(User customer) throws SQLException {
        String query = "INSERT INTO users (username, password, role, full_name, phone_number, identity_card_number, created_at, updated_at) " +
                "VALUES (?, ?, 'customer', ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getFullName());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getIdentityCardNumber());
            stmt.setTimestamp(6, Timestamp.valueOf(customer.getCreatedAt()));
            stmt.setTimestamp(7, Timestamp.valueOf(customer.getUpdatedAt()));
            stmt.executeUpdate();
        }
    }

    public void updateCustomer(User customer) throws SQLException {
        String query = "UPDATE users SET full_name = ?, phone_number = ?, identity_card_number = ?, updated_at = ? WHERE user_id = ?";
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
        String query = "DELETE FROM users WHERE user_id = ? AND role = 'customer'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
    }

    public List<User> searchCustomers(String keyword) throws SQLException {
        String query = "SELECT * FROM users WHERE role = 'customer' AND (full_name LIKE ? OR phone_number LIKE ? OR username LIKE ?)";
        List<User> customers = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User customer = new User();
                customer.setUserId(rs.getInt("user_id"));
                customer.setUsername(rs.getString("username"));
                customer.setFullName(rs.getString("full_name"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setIdentityCardNumber(rs.getString("identity_card_number"));
                customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                customers.add(customer);
            }
        }
        return customers;
    }
    
    
    public User getCustomerById(int customerId) {
        String query = "SELECT * FROM users WHERE user_id = ?"; // Truy vấn SQL để lấy thông tin khách hàng theo ID
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setIdentityCardNumber(rs.getString("identity_card_number"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
