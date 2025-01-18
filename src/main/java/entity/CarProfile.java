package entity;

import java.util.List;

public class CarProfile {

    private Long carId;
    private String licensePlate;
    private String brand;
    private String model;
    private Integer year;
    private String vin;
    private User user;
    private List<Schedule> schedules;
    private List<Maintenance> maintenances;

    public CarProfile() {
    }

    public CarProfile(Long carId, String licensePlate, String brand, String model, Integer year, String vin, User user,
                      List<Schedule> schedules, List<Maintenance> maintenances) {
        this.carId = carId;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vin = vin;
        this.user = user;
        this.schedules = schedules;
        this.maintenances = maintenances;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }
}
