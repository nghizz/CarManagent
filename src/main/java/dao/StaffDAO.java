package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class StaffDAO {
    private Connection conn;

    public StaffDAO() {
        this.conn = conn;
    }

    public List<User> getAllStaff() throws SQLException {
        String query = "SELECT * FROM users WHERE role = 'staff'";
        List<User> staffList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User staff = new User();
                staff.setUserId(rs.getInt("user_id"));
                staff.setUsername(rs.getString("username"));
                staff.setFullName(rs.getString("full_name"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setIdentityCardNumber(rs.getString("identity_card_number"));
                staff.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                staff.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                staffList.add(staff);
            }
        }
        return staffList;
    }

    public void addStaff(User staff) throws SQLException {
        String query = "INSERT INTO users (username, password, role, full_name, phone_number, identity_card_number, created_at, updated_at) " +
                "VALUES (?, ?, 'staff', ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, staff.getUsername());
            stmt.setString(2, staff.getPassword());
            stmt.setString(3, staff.getFullName());
            stmt.setString(4, staff.getPhoneNumber());
            stmt.setString(5, staff.getIdentityCardNumber());
            stmt.setTimestamp(6, Timestamp.valueOf(staff.getCreatedAt()));
            stmt.setTimestamp(7, Timestamp.valueOf(staff.getUpdatedAt()));
            stmt.executeUpdate();
        }
    }

    public void updateStaff(User staff) throws SQLException {
        String query = "UPDATE users SET full_name = ?, phone_number = ?, identity_card_number = ?, updated_at = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, staff.getFullName());
            stmt.setString(2, staff.getPhoneNumber());
            stmt.setString(3, staff.getIdentityCardNumber());
            stmt.setTimestamp(4, Timestamp.valueOf(staff.getUpdatedAt()));
            stmt.setInt(5, staff.getUserId());
            stmt.executeUpdate();
        }
    }

    public void deleteStaff(int staffId) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ? AND role = 'staff'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, staffId);
            stmt.executeUpdate();
        }
    }

    public List<User> searchStaff(String keyword) throws SQLException {
        String query = "SELECT * FROM users WHERE role = 'staff' AND (full_name LIKE ? OR phone_number LIKE ? OR username LIKE ?)";
        List<User> staffList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User staff = new User();
                staff.setUserId(rs.getInt("user_id"));
                staff.setUsername(rs.getString("username"));
                staff.setFullName(rs.getString("full_name"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setIdentityCardNumber(rs.getString("identity_card_number"));
                staff.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                staff.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                staffList.add(staff);
            }
        }
        return staffList;
    }
}
