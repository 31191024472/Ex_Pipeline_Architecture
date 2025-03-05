package vn.edu.ueh.bit.pipes.core.entities;

public class Payment {
    private long id;
    private String method;
    private String cardNumber;
    private String date;

    // Getters và Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
