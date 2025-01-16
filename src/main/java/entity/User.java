package entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "role", nullable = false, length = 255)
    private String role;

    @Column(name = "fullName", length = 255)
    private String fullName;

    @Column(name = "phoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "identityCardNumber", length = 20)
    private String identityCardNumber;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<CarProfile> carProfiles;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "technician")
    private List<Schedule> technicianSchedules;

    @OneToMany(mappedBy = "customer")
    private List<Maintenance> customerMaintenances;

    @OneToMany(mappedBy = "technician")
    private List<Maintenance> technicianMaintenances;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<CarProfile> getCarProfiles() {
		return carProfiles;
	}

	public void setCarProfiles(List<CarProfile> carProfiles) {
		this.carProfiles = carProfiles;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<Schedule> getTechnicianSchedules() {
		return technicianSchedules;
	}

	public void setTechnicianSchedules(List<Schedule> technicianSchedules) {
		this.technicianSchedules = technicianSchedules;
	}

	public List<Maintenance> getCustomerMaintenances() {
		return customerMaintenances;
	}

	public void setCustomerMaintenances(List<Maintenance> customerMaintenances) {
		this.customerMaintenances = customerMaintenances;
	}

	public List<Maintenance> getTechnicianMaintenances() {
		return technicianMaintenances;
	}

	public void setTechnicianMaintenances(List<Maintenance> technicianMaintenances) {
		this.technicianMaintenances = technicianMaintenances;
	}

    // Constructors, getters and setters
    
    
}