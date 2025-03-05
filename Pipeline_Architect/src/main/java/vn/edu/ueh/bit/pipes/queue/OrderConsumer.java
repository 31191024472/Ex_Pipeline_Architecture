package vn.edu.ueh.bit.pipes.queue;

import vn.edu.ueh.bit.pipes.bank.BankService;
import vn.edu.ueh.bit.pipes.core.Invoice;
import vn.edu.ueh.bit.pipes.core.Note;
import vn.edu.ueh.bit.pipes.core.Message;
import vn.edu.ueh.bit.pipes.process.invoice.InvoiceGenerator;
import vn.edu.ueh.bit.pipes.service.InventoryService;
import vn.edu.ueh.bit.pipes.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderConsumer {
    private final InventoryService inventoryService;
    private final DeliveryService deliveryService;
    private final BankService bankService;
    private final ObjectMapper objectMapper;

    public OrderConsumer(InventoryService inventoryService, DeliveryService deliveryService, BankService bankService) {
        this.inventoryService = inventoryService;
        this.deliveryService = deliveryService;
        this.bankService = bankService;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "orderQueue")
    public void processOrder(String messageJson) {
        try {
            Message message = objectMapper.readValue(messageJson, Message.class);
//            List<Invoice> orders = message.getInvoiceInfo().getInvoices();

            List<Note> notes = message.getInvoiceInfo().getNotes();
            if (notes != null && !notes.isEmpty()) {
                Note deliveryInfo = notes.get(0);

                // Tiến hành xử lý giao nhận
                if (!deliveryService.checkDelivery(deliveryInfo)) {
                    System.out.println("❌ Đơn hàng bị từ chối: Không hỗ trợ giao hàng!");
                    return;
                }
            } else {
                System.out.println("❌ Không có thông tin giao nhận!");
                return;
            }
            // 3️⃣ Kiểm tra khả năng thanh toán với BankService
            String cardNumber = message.getInvoiceInfo().getPayments().get(0).getCardNumber(); // Giả sử bạn lấy thông tin thẻ từ Message
            String cvv = message.getInvoiceInfo().getPayments().get(0).getCvv(); // Giả sử bạn lấy CVV từ Message
            if (!bankService.canProcessPayment(cardNumber, cvv, message.getInvoiceInfo())) {
                System.out.println("❌ Không đủ khả năng thanh toán.");
                return;
            }

            // 4️⃣ Thực hiện thanh toán qua BankService
            boolean paymentResult = bankService.processPayment(cardNumber, cvv, message.getInvoiceInfo());
            if (paymentResult) {
                System.out.println("✅ Thanh toán thành công!");

                // 5️⃣ Lập đơn hàng sau khi thanh toán thành công
                InvoiceGenerator.generateInvoice(message);
                System.out.println("✅ Đơn hàng đã được lập thành công!");
            } else {
                System.out.println("❌ Thanh toán thất bại.");
            }

            System.out.println("✅ Đơn hàng hợp lệ, tiếp tục xử lý...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
