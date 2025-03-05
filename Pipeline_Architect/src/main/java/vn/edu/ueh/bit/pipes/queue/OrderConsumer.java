package vn.edu.ueh.bit.pipes.queue;

import vn.edu.ueh.bit.pipes.core.Invoice;
import vn.edu.ueh.bit.pipes.core.Note;
import vn.edu.ueh.bit.pipes.core.Message;
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
    private final ObjectMapper objectMapper;

    public OrderConsumer(InventoryService inventoryService, DeliveryService deliveryService) {
        this.inventoryService = inventoryService;
        this.deliveryService = deliveryService;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "orderQueue")
    public void processOrder(String messageJson) {
        System.out.println("📥 Nhận đơn hàng từ queue: " + messageJson);
        try {
            Message message = objectMapper.readValue(messageJson, Message.class);
            List<Invoice> orders = message.getInvoiceInfo().getInvoices();
            Note deliveryInfo = message.getInvoiceInfo().getNotes().get(0);

            // 1️⃣ Kiểm tra hàng tồn kho
            if (!inventoryService.checkStock(orders)) {
                System.out.println("❌ Đơn hàng bị từ chối: Hết hàng!");
                return;
            }

            // 2️⃣ Kiểm tra dịch vụ giao nhận
            if (!deliveryService.checkDelivery(deliveryInfo)) {
                System.out.println("❌ Đơn hàng bị từ chối: Không hỗ trợ giao hàng!");
                return;
            }

            System.out.println("✅ Đơn hàng hợp lệ, tiếp tục xử lý...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
