package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.CarProfile;
import entity.Maintenance;
import entity.Schedule;
import entity.User;

public class CarProfileDAO {

    // Replace DriverManager connection with the custom getConnection method
    public static Connection getConnection() throws SQLException {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the updated driver class name
            String url = "jdbc:mysql://localhost:3306/carmanagement";
            String username = "root";
            String password = "";
            c = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // Lấy tất cả các xe
    public List<CarProfile> getAllCars() throws ClassNotFoundException {
        List<CarProfile> carList = new ArrayList<>();
        String sql = "SELECT * FROM CarProfile";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Lấy thông tin user từ user_id
                User user = getUserById(resultSet.getLong("user_id"));

                // Lấy các thông tin cần thiết từ CarProfile và thêm vào danh sách
                CarProfile car = new CarProfile(
                        resultSet.getLong("carId"),
                        resultSet.getString("licensePlate"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("vin"),
                        user,
                        getSchedulesByCarId(resultSet.getLong("carId")),  // Lấy danh sách lịch bảo dưỡng
                        getMaintenancesByCarId(resultSet.getLong("carId")) // Lấy danh sách bảo dưỡng
                );
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carList;
    }

    // Lấy thông tin User dựa trên userId
    public User getUserById(long userId) {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Construct and return User object (make sure to implement User constructor)
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách Schedule dựa trên carId
    private List<Schedule> getSchedulesByCarId(long carId) {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM Schedule WHERE car_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, carId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Construct and add Schedule object to list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    // Lấy danh sách Maintenance dựa trên carId
    private List<Maintenance> getMaintenancesByCarId(long carId) {
        List<Maintenance> maintenances = new ArrayList<>();
        String sql = "SELECT * FROM Maintenance WHERE car_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, carId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Construct and add Maintenance object to list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenances;
    }

    // Thêm một chiếc xe mới
    public void addCar(CarProfile car) {
        String sql = "INSERT INTO CarProfile (licensePlate, brand, model, year, vin, user_id, createdAt, updatedAt) " +
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

    // Cập nhật thông tin của một chiếc xe
    public void updateCar(CarProfile car) {
        String sql = "UPDATE CarProfile SET licensePlate = ?, brand = ?, model = ?, year = ?, vin = ?, user_id = ?, updatedAt = NOW() " +
                     "WHERE carId = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, car.getLicensePlate());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getYear());
            preparedStatement.setString(5, car.getVin());
            preparedStatement.setLong(6, car.getUser().getUserId()); // Lấy ID user
            preparedStatement.setLong(7, car.getCarId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa một chiếc xe
    public void deleteCar(long carId) {
        String sql = "DELETE FROM CarProfile WHERE carId = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public CarProfile getCarById(long carId) {
		// TODO Auto-generated method stub
		return null;
	}
}
