package vn.edu.ueh.bit.pipes.core;

public class Payment {
    private long paymentId;
    private long custId;
    private String cardNumber;
    private String cvv;

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
    public String toJson() { return "{ \"paymentId\": " + paymentId + " }"; }
}
