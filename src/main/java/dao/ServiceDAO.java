package dao;

import java.sql.*;
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
}
