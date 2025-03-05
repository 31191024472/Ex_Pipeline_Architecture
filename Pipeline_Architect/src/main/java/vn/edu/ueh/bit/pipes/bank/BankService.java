package vn.edu.ueh.bit.pipes.bank;

import org.springframework.stereotype.Service;
import vn.edu.ueh.bit.pipes.core.InvoiceInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BankService {
    private Map<String, BankAccount> accounts;

    public BankService() {
        accounts = new HashMap<>();
        accounts.put("1234-5678-9876-5432", new BankAccount("7485-2222-3456-2435", "111", 800, 500));
    }
    //
    public Optional<BankAccount> findAccount(String cardNumber, String cvv) {
        return accounts.values().stream()
                .filter(acc -> acc.getCardNumber().equals(cardNumber) && acc.getCvv().equals(cvv))
                .findFirst();
    }
    // Kiểm tra đủ năng lực thanh toán không
    public boolean canProcessPayment(String cardNumber, String cvv, InvoiceInfo invoiceInfo) {
        double amount = invoiceInfo.getTotalAmount(); // Lấy tổng tiền từ hóa đơn
        return findAccount(cardNumber, cvv)
                .map(account -> {
                    if ((account.getBalance() + account.getCreditLimit()) >= amount) {
                        System.out.println("Tài khoản đủ tiền để thanh toán đơn hàng.");
                        return true;
                    } else {
                        System.out.println("Tài khoản không đủ tiền để thanh toán đơn hàng.");
                        return false;
                    }
                }).orElse(false);
    }

    // Thực hiện thanh toán
    public boolean processPayment(String cardNumber, String cvv, InvoiceInfo invoiceInfo) {
        double amount = invoiceInfo.getTotalAmount(); // Lấy tổng tiền từ hóa đơn
        return findAccount(cardNumber, cvv)
                .map(account -> {
                    if ((account.getBalance() + account.getCreditLimit()) >= amount) {
                        if (account.getBalance() >= amount) {
                            account.deductAmount(amount); // Trừ từ số dư
                        } else {
                            double remainingAmount = amount - account.getBalance();
                            account.setBalance(0);
                            account.setCreditLimit(account.getCreditLimit() - remainingAmount); // Trừ vào hạn mức tín dụng
                        }
                        System.out.println("Thanh toán thành công!");
                        return true;
                    } else {
                        System.out.println("Thanh toán thất bại!");
                        return false;
                    }
                }).orElse(false);

    }
}
