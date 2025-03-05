package vn.edu.ueh.bit.pipes.bank;

public class BankAccount {
    private String cardNumber;
    private String cvv;
    private double balance; // Số dư tài khoản
    private double creditLimit; // Hạn mức tín dụng

    public BankAccount(String cardNumber, String cvv, double balance, double creditLimit) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.balance = balance;
        this.creditLimit = creditLimit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void deductAmount(double amount) {
        this.balance -= amount; // Trừ tiền từ số dư
    }
}
