package vn.edu.ueh.bit.pipes.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    @JsonProperty("paymentId")
    private long paymentId;

    @JsonProperty("custId")
    private long custId;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("cvv")
    private String cvv;

    // Getter và Setter
    public void setPaymentId(long paymentId) { this.paymentId = paymentId; }
    public long getPaymentId() { return paymentId; }

    public void setCustId(long custId) { this.custId = custId; }
    public long getCustId() { return custId; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardNumber() { return cardNumber; }

    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getCvv() { return cvv; }

    @Override
    public String toString() { return "Payment: " + paymentId; }

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
