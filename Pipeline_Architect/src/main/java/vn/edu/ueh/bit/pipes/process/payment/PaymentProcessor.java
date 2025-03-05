package vn.edu.ueh.bit.pipes.process.payment;

import vn.edu.ueh.bit.pipes.bank.BankService;
import vn.edu.ueh.bit.pipes.core.Payment;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.Iterator;
import java.util.List;

public class PaymentProcessor implements IFilter<IMessage> {

    private final BankService bankService;

    public PaymentProcessor(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public IMessage execute(IMessage message) {
        double totalAmount = message.getInvoiceInfo().getTotalAmount();
        System.out.println("💵 Tổng số tiền cần thanh toán: " + totalAmount);

        List<Payment> payments = message.getInvoiceInfo().getPayments();
        if (payments == null || payments.isEmpty()) {
            System.out.println(" Không có phương thức thanh toán nào. Hủy đơn hàng!");
            return null;
        }

        // ✅ Kiểm tra xem có ít nhất một tài khoản đủ điều kiện thanh toán không
        boolean hasValidPayment = payments.stream()
                .anyMatch(payment -> bankService.canProcessPayment(
                        payment.getCardNumber(),
                        payment.getCvv(),
                        message.getInvoiceInfo()));

        if (!hasValidPayment) {
            System.out.println(" Không có tài khoản nào đủ tiền. Hủy đơn hàng!");
            return null;
        }

        // ✅ Nếu có tài khoản đủ tiền, tiến hành thanh toán với từng Payment
        Iterator<Payment> iterator = payments.iterator();
        while (iterator.hasNext()) {
            Payment payment = iterator.next();

            boolean success = bankService.processPayment(
                    payment.getCardNumber(),
                    payment.getCvv(),
                    message.getInvoiceInfo());

            if (success) {
                System.out.println(" Thanh toán thành công với thẻ: " + payment.getCardNumber());
                return message; // Nếu thanh toán thành công, tiếp tục xử lý đơn hàng
            } else {
                System.out.println(" Thanh toán thất bại với thẻ: " + payment.getCardNumber());
                iterator.remove(); // Loại bỏ thẻ thanh toán không hợp lệ
            }
        }

        System.out.println(" Không có phương thức thanh toán hợp lệ. Hủy đơn hàng!");
        return null;
    }

}
