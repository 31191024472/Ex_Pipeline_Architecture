package vn.edu.ueh.bit.pipes.service;

import com.rabbitmq.client.*;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Random;

public class InvoiceService {
    private static final String PAYMENT_CHECKED_QUEUE = "payment_checked_queue";
    private static final String ORDER_COMPLETED_QUEUE = "order_completed_queue"; // Đổi queue này để EmailService có thể lắng nghe

    public void start() {
        System.out.println("🧾 InvoiceService is ready to generate invoices...");
        processInvoices();
    }

    private void processInvoices() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(PAYMENT_CHECKED_QUEUE, true, false, false, null);
            channel.queueDeclare(ORDER_COMPLETED_QUEUE, true, false, false, null); // Đổi queue này

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("📥 Received order for invoicing: " + message);

                String invoiceMessage = generateInvoice(message);
                if (invoiceMessage != null) {
                    channel.basicPublish("", ORDER_COMPLETED_QUEUE, null, invoiceMessage.getBytes(StandardCharsets.UTF_8));
                    System.out.println("✅ Invoice created and forwarded to ORDER_COMPLETED_QUEUE");
                }
            };

            channel.basicConsume(PAYMENT_CHECKED_QUEUE, true, deliverCallback, consumerTag -> {});

        } catch (Exception e) {
            System.err.println("❌ ERROR in InvoiceService: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generateInvoice(String orderJson) {
        JSONObject orderObject = new JSONObject(orderJson);
        int invoiceId = new Random().nextInt(1000000);
        LocalDateTime invoiceDate = LocalDateTime.now();

        // Thêm thông tin hóa đơn vào đơn hàng
        JSONObject invoice = new JSONObject();
        invoice.put("invoiceId", invoiceId);
        invoice.put("invoiceDate", invoiceDate.toString());

        orderObject.put("invoice", invoice);
        return orderObject.toString();
    }
}
