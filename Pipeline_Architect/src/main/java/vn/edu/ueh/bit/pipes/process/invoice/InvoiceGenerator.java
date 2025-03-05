package vn.edu.ueh.bit.pipes.process.invoice;

import vn.edu.ueh.bit.pipes.core.Invoice;
import vn.edu.ueh.bit.pipes.core.InvoiceInfo;
import vn.edu.ueh.bit.pipes.core.Message;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceGenerator {
    public static void generateInvoice(Message message) {
        // Kiểm tra và khởi tạo invoiceInfo nếu null
        if (message.getInvoiceInfo() == null) {
            message.setInvoiceInfo(new InvoiceInfo());  // Khởi tạo mới
        }

        // Kiểm tra xem invoices có null không trước khi thực hiện thao tác
        List<Invoice> invoices = message.getInvoiceInfo().getInvoices() != null ?
                message.getInvoiceInfo().getInvoices().stream()
                        .map(order -> new Invoice(
                                order.getInvoiceId(),
                                order.getCustId(),
                                order.getProductId(),
                                order.getQuantity(),
                                order.getPrice(),
                                order.getDescription()))
                        .collect(Collectors.toList()) :
                List.of();  // Nếu invoices là null, tạo danh sách rỗng

        // Cập nhật lại danh sách invoices trong invoiceInfo
        message.getInvoiceInfo().setInvoices(invoices);

        // In ra thông tin đã tạo hóa đơn
        System.out.println("📜 Hóa đơn đã được tạo:\n" + message.getInvoiceInfo().toJson());
    }
}
