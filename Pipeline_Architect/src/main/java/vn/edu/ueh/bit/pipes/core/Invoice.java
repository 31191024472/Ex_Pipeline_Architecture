package vn.edu.ueh.bit.pipes.core;

public class Invoice {
    private long invoiceId;
    private long custId;
    private long productId;
    private long quantity;
    private double price; // Giá sản phẩm
    private String description;

    public Invoice(long invoiceId, long custId, long productId, long quantity, double price, String description) {
        this.invoiceId = invoiceId;
        this.custId = custId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }
    public void setInvoiceId(long invoiceId) { this.invoiceId = invoiceId; }
    public long getInvoiceId() { return invoiceId; }
    public void setCustId(long custId) { this.custId = custId; }
    public long getCustId() { return custId; }
    public void setProductId(long productId) { this.productId = productId; }
    public long getProductId() { return productId; }
    public void setQuantity(long quantity) { this.quantity = quantity; }
    public long getQuantity() { return quantity; }
    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() { return "Invoice: " + invoiceId; }
    public String toJson() { return "{ \"invoiceId\": " + invoiceId + " }"; }
}
