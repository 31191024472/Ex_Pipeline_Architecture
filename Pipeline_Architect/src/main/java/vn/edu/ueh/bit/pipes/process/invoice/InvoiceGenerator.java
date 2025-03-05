package vn.edu.ueh.bit.pipes.process.invoice;

import vn.edu.ueh.bit.pipes.core.Invoice;
import vn.edu.ueh.bit.pipes.core.Message;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.List;
import java.util.stream.Collectors;

public class InvoiceGenerator {
    public static void generateInvoice(Message message) {
        List<Invoice> invoices = message.getInvoiceInfo().getInvoices().stream()
                .map(order -> new Invoice(
                        order.getInvoiceId(),
                        order.getCustId(),
                        order.getProductId(),
                        order.getQuantity(),
                        order.getPrice(),
                        order.getDescription()))
                .collect(Collectors.toList());
        message.getInvoiceInfo().setInvoices(invoices);
        System.out.println("📜 Hóa đơn đã được tạo:\n" + message.getInvoiceInfo().toJson());
    }

}
