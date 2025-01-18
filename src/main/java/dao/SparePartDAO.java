package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import entity.SpareParts;
import utils.DBUtils;

public class SparePartDAO {
    private Connection connection;

    public SparePartDAO() {
        this.connection = DBUtils.getConnection();
    }

    // Lấy danh sách phụ tùng
    public List<SpareParts> getAllSpareParts() {
        List<SpareParts> spareParts = new ArrayList<>();
        String query = "SELECT * FROM spareparts";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SpareParts sparePart = new SpareParts(
                    rs.getLong("partId"), // Sử dụng Long vì partId là kiểu Long
                    rs.getString("partName"),
                    rs.getString("partCode"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("price"),
                    rs.getDate("createdAt"),
                    rs.getDate("updatedAt"), // Cập nhật với trường 'updatedAt' trong SQL
                    rs.getString("imageUrl") // Đảm bảo rằng imageUrl được bao gồm trong câu SQL của bạn
                );
                spareParts.add(sparePart);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Bạn có thể ghi log thay vì in stack trace ở đây.
        }
        return spareParts;
    }

    // Theo dõi nhập xuất tồn kho
    public boolean updateStock(int sparePartId, int quantity) {
        String query = "UPDATE spareparts SET quantity = quantity + ? WHERE partId = ?"; // Sửa lại tên cột là 'partId'
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantity); // Cập nhật số lượng
            stmt.setLong(2, sparePartId); // Id của phụ tùng, 'partId' là kiểu Long
            int rowsUpdated = stmt.executeUpdate(); // Số dòng bị ảnh hưởng
            return rowsUpdated > 0; // Nếu có dòng được cập nhật, trả về true
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi hoặc thông báo phù hợp
        }
        return false; // Trả về false nếu không có thay đổi
    }
}
