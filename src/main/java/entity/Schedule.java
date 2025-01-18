package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Schedule {

    private Long scheduleId;
    private LocalDateTime serviceDate;
    private String status;
    private String carCondition;
    private User user;
    private CarProfile car;
    private User technician;
    private Invoice invoice;
    private List<Service> services;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
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

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarProfile getCar() {
        return car;
    }
}
