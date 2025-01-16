package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import entity.User;
import utils.DBUtils;

public class AppointmentDAO {
    private Connection connection;

    public AppointmentDAO() {
        this.connection = DBUtils.getConnection();
    }

    // Lấy danh sách lịch hẹn
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointment ORDER BY appointment_date, appointment_time";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Appointment appointment = new Appointment(
                    rs.getInt("id"),
                    rs.getInt("customer_id"),
                    rs.getInt("car_id"),
                    rs.getInt("service_id"),
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getInt("employee_id")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Cập nhật trạng thái lịch hẹn
    public boolean updateStatus(int appointmentId, String status) {
        String query = "UPDATE appointment SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, appointmentId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phân công nhân viên
    public boolean assignEmployee(int appointmentId, int employeeId) {
        String query = "UPDATE appointment SET employee_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, appointmentId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Gửi thông báo (Giả sử gửi email và SMS đã được tích hợp)
    public void sendNotification(int appointmentId, String message) {
        Appointment appointment = getAppointmentById(appointmentId);
        CustomerDAO customerDAO = new CustomerDAO();
        User customer = customerDAO.getCustomerById(appointment.getCustomerId());
        String phone = customer.getPhoneNumber();
        
        SMSService.sendSMS(phone, message);
    }

    // Lấy thông tin lịch hẹn theo ID
    private Appointment getAppointmentById(int appointmentId) {
        String query = "SELECT * FROM appointment WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Appointment(
                    rs.getInt("id"),
                    rs.getInt("customer_id"),
                    rs.getInt("car_id"),
                    rs.getInt("service_id"),
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getInt("employee_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
