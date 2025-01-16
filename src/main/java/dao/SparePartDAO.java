package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT * FROM spare_part";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	SpareParts sparePart = new SpareParts(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("code"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("price"),
                    rs.getString("supplier"),
                    rs.getDate("expiry_date")
                );
                spareParts.add(sparePart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spareParts;
    }

    // Theo dõi nhập xuất tồn
    public boolean updateStock(int sparePartId, int quantity) {
        String query = "UPDATE spare_part SET quantity = quantity + ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, sparePartId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
