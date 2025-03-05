package vn.edu.ueh.bit.pipes.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Invoice {
    private long invoiceId;
    private long custId;
    private long productId;
    private long quantity;
    private String description;

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

    @Override
    public String toString() { return "Invoice: " + invoiceId; }
    public String toJson() { return "{ \"invoiceId\": " + invoiceId + " }"; }
}
