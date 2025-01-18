package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Maintenance {

    private Long maintenanceId;
    private CarProfile car;
    private User customer;
    private User technician;
    private LocalDateTime serviceDate;
    private String status;
    private String notes;
    private List<SpareParts> spareParts;

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public CarProfile getCar() {
        return car;
    }

    public void setCar(CarProfile car) {
        this.car = car;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getTechnician() {
        return technician;
    }

    public void setTechnician(User technician) {
        this.technician = technician;
    }

    public LocalDateTime getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDateTime serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<SpareParts> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(List<SpareParts> spareParts) {
        this.spareParts = spareParts;
    }

    // Constructors, toString, equals, and hashCode (optional)
}
