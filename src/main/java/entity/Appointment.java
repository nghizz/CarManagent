package entity;

import java.sql.Timestamp;

public class Appointment {
    private int id;
    private int customerId;
    private int carId;
    private int serviceId;
    private Timestamp appointmentDate;
    private String status;
    private int employeeId;

    // Constructor
    public Appointment(int id, int customerId, int carId, int serviceId, Timestamp appointmentDate, String status, int employeeId) {
        this.id = id;
        this.customerId = customerId;
        this.carId = carId;
        this.serviceId = serviceId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.employeeId = employeeId;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // toString method for better display (optional)
    @Override
    public String toString() {
        return "Appointment{id=" + id + ", customerId=" + customerId + ", carId=" + carId +
               ", serviceId=" + serviceId + ", appointmentDate=" + appointmentDate +
               ", status='" + status + "', employeeId=" + employeeId + "}";
    }
}
