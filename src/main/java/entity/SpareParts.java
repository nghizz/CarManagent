package entity;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "spareparts")
public class SpareParts {

    public SpareParts(int int1, String string, String string2, int int2, BigDecimal bigDecimal, String string3, Date date) {
		// TODO Auto-generated constructor stub
	}

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