	package dao;
	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Timestamp;
	import java.util.ArrayList;
	import java.util.List;
	import utils.DBUtils;
	
	import entity.User;
	
	public class StaffDAO {
	    private Connection conn;

	    public StaffDAO() {
	        this.conn = DBUtils.getConnection(); // Sử dụng DBUtils để lấy kết nối
	    }

	    public User loginUser(String username, String password) throws SQLException {
	        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
	        User user = null;
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                user = new User();
	                user.setUserId(rs.getInt("userId"));
	                user.setUsername(rs.getString("username"));
	                user.setFullName(rs.getString("fullName"));
	                user.setPhoneNumber(rs.getString("phoneNumber"));
	                user.setIdentityCardNumber(rs.getString("identityCardNumber"));
	                user.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
	                user.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
	            }
	        }
	        return user; // Trả về đối tượng User nếu tìm thấy, hoặc null nếu không tìm thấy
	    }

	    public List<User> getAllStaff() throws SQLException {
	        String query = "SELECT * FROM users WHERE role = 'employee'";
	        List<User> staffList = new ArrayList<>();
	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                User staff = new User();
	                staff.setUserId(rs.getInt("userId"));
	                staff.setUsername(rs.getString("username"));
	                staff.setFullName(rs.getString("fullName"));
	                staff.setPhoneNumber(rs.getString("phoneNumber"));
	                staff.setIdentityCardNumber(rs.getString("identityCardNumber"));
	                staff.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
	                staff.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
	                staffList.add(staff);
	            }
	        }
	        return staffList;
	    }

	    public void addStaff(User staff) throws SQLException {
	        String query = "INSERT INTO users (username, password, role, fullName, phoneNumber, identityCardNumber, createdAt, updatedAt) " +
	                "VALUES (?, ?, 'employee', ?, ?, ?, ?, ?)";
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
	        String query = "UPDATE users SET fullName = ?, phoneNumber = ?, identityCardNumber = ?, updatedAt = ? WHERE userId = ?";
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
	        String query = "DELETE FROM users WHERE userId = ? AND role = 'employee'";
	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, staffId);
	            stmt.executeUpdate();
	        }
	    }

	    public List<User> searchStaff(String keyword) throws SQLException {
	        String query = "SELECT * FROM users WHERE role = 'employee' AND (full_name LIKE ? OR phone_number LIKE ? OR username LIKE ?)";
	        List<User> staffList = new ArrayList<>();
	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, "%" + keyword + "%");
	            stmt.setString(2, "%" + keyword + "%");
	            stmt.setString(3, "%" + keyword + "%");
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                User staff = new User();
	                staff.setUserId(rs.getInt("userId"));
	                staff.setUsername(rs.getString("username"));
	                staff.setFullName(rs.getString("fullName"));
	                staff.setPhoneNumber(rs.getString("phoneNumber"));
	                staff.setIdentityCardNumber(rs.getString("identityCardNumber"));
	                staff.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
	                staff.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
	                staffList.add(staff);
	            }
	        }
	        return staffList;
	    }
	}

