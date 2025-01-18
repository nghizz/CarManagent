package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.CarProfile;
import entity.User;
import utils.DBUtils;

public class CarProfileDAO {

    public static Connection getConnection() throws SQLException {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/carmanagement";
            String username = "root";
            String password = "";
            c = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setFullName(resultSet.getString("full_name"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setIdentityCardNumber(resultSet.getString("identity_card_number"));
                user.setRole(resultSet.getString("role"));
                user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                user.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<CarProfile> getAllCars() {
        List<CarProfile> carProfiles = new ArrayList<>();
        String sql = "SELECT * FROM carprofile";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                long userId = rs.getLong("user_id");  // Lấy user_id từ bảng carprofile
                User user = getUserById(userId);  // Gọi phương thức getUserById để lấy thông tin người dùng
                CarProfile car = new CarProfile(
                        rs.getLong("carId"),
                        rs.getString("licensePlate"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getString("vin"),
                        user,  // Thiết lập user cho car profile
                        null, null);
                carProfiles.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carProfiles;
    }


    public CarProfile getCarById(long carId) {
        String sql = "SELECT * FROM carprofile WHERE carId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, carId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CarProfile(
                        rs.getLong("carId"),
                        rs.getString("licensePlate"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getString("vin"),
                        null, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(long userId) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCar(CarProfile car) {
        String sql = "UPDATE carprofile SET licensePlate = ?, brand = ?, model = ?, year = ?, vin = ?, user_id = ? WHERE carId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getLicensePlate());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setString(5, car.getVin());
            stmt.setLong(6, car.getUser().getUserId());
            stmt.setLong(7, car.getCarId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(long carId) {
        String sql = "DELETE FROM carprofile WHERE carId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, carId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(CarProfile car) {
        String sql = "INSERT INTO carprofile (licensePlate, brand, model, year, vin, user_id, createdAt, updatedAt) " +
                     "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, car.getLicensePlate());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getYear());
            preparedStatement.setString(5, car.getVin());
            preparedStatement.setLong(6, car.getUser().getUserId()); // Lấy ID user

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
