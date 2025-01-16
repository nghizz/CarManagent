package dao;

import entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Lấy danh sách khách hàng
    public List<User> getAllCustomers() {
        List<User> customers = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 'CUSTOMER'"; // Lấy danh sách khách hàng
        try (Connection conn = UserDAO.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                User customer = new User();
                customer.setUserId(rs.getLong("userId"));
                customer.setUsername(rs.getString("username"));
                customer.setFullName(rs.getString("fullName"));
                customer.setPhoneNumber(rs.getString("phone"));
                customer.setIdentityCardNumber(rs.getString("identityCardNumber"));
                customer.setCreatedAt(rs.getObject("createdAt", java.time.LocalDateTime.class));
                customer.setUpdatedAt(rs.getObject("updatedAt", java.time.LocalDateTime.class));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Thêm khách hàng mới
    public void addCustomer(User customer) {
        String query = "INSERT INTO users (username, role, fullName, phoneNumber, identityCardNumber, createdAt, updatedAt) "
                     + "VALUES (?, 'CUSTOMER', ?, ?, ?, ?, ?)";
        try (Connection conn = UserDAO.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getIdentityCardNumber());
            stmt.setObject(5, customer.getCreatedAt());
            stmt.setObject(6, customer.getUpdatedAt());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật thông tin khách hàng
    public void updateCustomer(User customer) {
        String query = "UPDATE users SET fullName = ?, phone = ?, identityCardNumber = ?, updatedAt = ? WHERE userId = ?";
        try (Connection conn = UserDAO.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(4, customer.getIdentityCardNumber());
            stmt.setObject(5, customer.getUpdatedAt());
            stmt.setLong(6, customer.getUserId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa khách hàng
    public void deleteCustomer(long userId) {
        String query = "DELETE FROM users WHERE userId = ?";
        try (Connection conn = UserDAO.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm khách hàng theo tên
    public List<User> searchCustomers(String name) {
        List<User> customers = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 'CUSTOMER' AND fullName LIKE ?";
        try (Connection conn = UserDAO.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                User customer = new User();
                customer.setUserId(rs.getLong("userId"));
                customer.setUsername(rs.getString("username"));
                customer.setFullName(rs.getString("fullName"));
                customer.setPhoneNumber(rs.getString("phone"));
                customer.setIdentityCardNumber(rs.getString("identityCardNumber"));
                customer.setCreatedAt(rs.getObject("createdAt", java.time.LocalDateTime.class));
                customer.setUpdatedAt(rs.getObject("updatedAt", java.time.LocalDateTime.class));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
