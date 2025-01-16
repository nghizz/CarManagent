package entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintenanceId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarProfile car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;

    private LocalDateTime serviceDate;

    private String status;

    private String notes;

    @ManyToMany
    @JoinTable(
            name = "maintenance_spareparts",
            joinColumns = @JoinColumn(name = "maintenance_id"),
            inverseJoinColumns = @JoinColumn(name = "spareparts_id")
    )
    private List<SpareParts> spareParts;

    // Constructors, getters and setters
}