package entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    private LocalDateTime serviceDate;

    private String status;

    private String carCondition;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarProfile car;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;

    @OneToOne(mappedBy = "schedule")
    private Invoice invoice;

    @ManyToMany
    @JoinTable(
            name = "schedule_service",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services;

    // Constructors, getters and setters
}