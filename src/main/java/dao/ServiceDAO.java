package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Service;
import utils.DBUtils;

public class ServiceDAO {
    private Connection connection;

    public ServiceDAO() {
        this.connection = DBUtils.getConnection();
    }

    // Lấy danh sách dịch vụ
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM service";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Service service = new Service(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    // Thêm dịch vụ
    public boolean addService(Service service) {
    	String query = "INSERT INTO service (name, description, price) VALUES (?, ?, ?)"; 
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, service.getServiceName());
            stmt.setString(2, service.getDescription());
            stmt.setDouble(3, service.getPrice());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật giá dịch vụ
    public boolean updateServicePrice(int serviceId, double price) {
        String query = "UPDATE service SET price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, price);
            stmt.setInt(2, serviceId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa dịch vụ
    public boolean deleteService(int serviceId) {
        String query = "DELETE FROM service WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, serviceId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
