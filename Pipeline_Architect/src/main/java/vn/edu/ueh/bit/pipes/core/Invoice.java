package vn.edu.ueh.bit.pipes.core;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Invoice {
    @JsonProperty("orderId")
    private long invoiceId;

    @JsonProperty("custId")
    private long custId;

    @JsonProperty("productId")
    private long productId;

    @JsonProperty("quantity")
    private long quantity;

    @JsonProperty("price")
    private double price;

    @JsonProperty("description")
    private String description;

    // Constructor mặc định
    public Invoice() {
    }
    public Invoice(long invoiceId, long custId, long productId, long quantity, double price, String description) {
        this.invoiceId = invoiceId;
        this.custId = custId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    // Getter và Setter
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

    // Phương thức chuyển đối tượng thành JSON
    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);  // Sử dụng Jackson để chuyển đổi
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
