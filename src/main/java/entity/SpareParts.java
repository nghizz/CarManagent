import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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