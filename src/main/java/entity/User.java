package entity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String fullName;

    private String phoneNumber;

    private String identityCardNumber;

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

    // Constructors, getters and setters
}