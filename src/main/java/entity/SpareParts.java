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
@Table(name = "SpareParts")
public class SpareParts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;

    @Column(nullable = false)
    private String partName;

    @Column(nullable = false, unique = true)
    private String partCode;

    private Integer quantity;

    private BigDecimal price;

    private String imageUrl;

    @ManyToMany(mappedBy = "spareParts")
    private List<Invoice> invoices;

    @ManyToMany(mappedBy = "spareParts")
    private List<Maintenance> maintenances;

    // Constructors, getters and setters
}