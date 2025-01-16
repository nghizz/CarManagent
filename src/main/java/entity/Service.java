package entity;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(nullable = false)
    private String serviceName;

    private String description;

    private BigDecimal price;

    @ManyToMany(mappedBy = "services")
    private List<Invoice> invoices;

    @ManyToMany(mappedBy = "services")
    private List<Schedule> schedules;

    // Constructors, getters and setters
}