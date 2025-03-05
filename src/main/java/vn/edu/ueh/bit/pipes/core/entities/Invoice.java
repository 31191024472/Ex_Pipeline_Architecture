package vn.edu.ueh.bit.pipes.core.entities;

public class Invoice {
    private long id;
    private long quantity;
    private String description;
    private long price;
    private long tax;
    private String dueDate;

    // Getters và Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getQuantity() { return quantity; }
    public void setQuantity(long quantity) { this.quantity = quantity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public long getTax() { return tax; }
    public void setTax(long tax) { this.tax = tax; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
}
