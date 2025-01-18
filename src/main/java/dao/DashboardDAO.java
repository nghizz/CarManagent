package dao;

import java.sql.*;

import utils.DBUtils;

public class DashboardDAO {

    public int getNewAppointments() throws Exception {
        String query = "SELECT COUNT(*) FROM maintenance WHERE status = 'Completed'";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public double getRevenue() throws Exception {
        String query = "SELECT SUM(amount) FROM payments";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }

    public int getTotalCars() throws Exception {
        String query = "SELECT COUNT(*) FROM cars";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error getting total cars", e);
        }
        return 0;
    }
}
