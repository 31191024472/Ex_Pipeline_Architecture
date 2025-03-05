package vn.edu.ueh.bit.pipes.process.payment;

import vn.edu.ueh.bit.pipes.core.Payment;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.List;

public class PaymentValidator implements IFilter<IMessage> {
    public boolean validatePayment(Payment payment) {
        String cardNumber = payment.getCardNumber();
        if (cardNumber == null || !cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
            return false;
        }
        String cvv = payment.getCvv();
        if (cvv == null || !cvv.matches("\\d{3}")) {
            return false;
        }
        return true;
    }
    @Override
    public IMessage execute(IMessage message) {
        List<Payment> payments = message.getInvoiceInfo().getPayments();
        payments.removeIf(payment -> !validatePayment(payment)); // Loại bỏ giao dịch không hợp lệ
        message.getInvoiceInfo().setPayments(payments);
        return message;
    }
}
