package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class SpareParts {
    private Long partId;
    private String partName;
    private String partCode;
    private Integer quantity;
    private BigDecimal price;
    private String Description;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
    private List<Invoice> invoices;
    private List<Maintenance> maintenances;

    // Constructor
    public SpareParts(long partId, String partName, String partCode, Integer quantity, BigDecimal price, Date createdAt, Date updatedAt, String imageUrl) {
        this.partId = partId;
        this.partName = partName;
        this.partCode = partCode;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	// Getters and Setters
    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }
}
