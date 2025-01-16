package entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CarProfile")
public class CarProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    private String brand;

    private String model;

    private Integer year;

    private String vin;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "car")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances;

    // Constructors, getters and setters
}