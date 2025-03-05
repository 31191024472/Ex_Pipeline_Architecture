package vn.edu.ueh.bit.pipes.service;

import com.rabbitmq.client.*;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class EmailService {
    private static final String ORDER_COMPLETED_QUEUE = "order_completed_queue";

    public void start() {
        System.out.println("📧 EmailService is ready to send order confirmation emails...");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(ORDER_COMPLETED_QUEUE, true, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("📨 New order completed! Preparing email notification...");

                // Giả lập email của khách hàng (ẩn thông tin cá nhân)
                String customerEmail = "customer@example.com";

                // Gửi email thông báo
                sendOrderConfirmation(message, customerEmail);
            };

            channel.basicConsume(ORDER_COMPLETED_QUEUE, true, deliverCallback, consumerTag -> {});

        } catch (Exception e) {
            System.err.println("❌ ERROR in EmailService: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendOrderConfirmation(String orderJson, String customerEmail) {
        try {
            JSONObject orderObject = new JSONObject(orderJson);
            JSONObject orderInfo = orderObject.getJSONObject("order_info");

            int orderId = orderInfo.getJSONObject("orders").getInt("orderId");
            int quantity = orderInfo.getJSONObject("orders").getInt("quantity");
            String deliveryAddress = orderInfo.getJSONObject("delivery").getString("deliveryAdd");
            boolean isPaid = orderObject.getBoolean("paymentChecked");
            int totalAmount = calculateTotalAmount(orderInfo);

            // Simulate email content as console output
            System.out.println("📩 Sending order confirmation email...");
            System.out.println("---------------------------------------------------");
            System.out.println("To: " + customerEmail);
            System.out.println("Subject: 🛒 Your Order #" + orderId + " Has Been Confirmed!");
            System.out.println("---------------------------------------------------");
            System.out.println("✅ Order ID: " + orderId);
            System.out.println("📦 Quantity: " + quantity);
            System.out.println("📍 Delivery Address: " + deliveryAddress);
            System.out.println("💳 Payment Status: " + (isPaid ? "Paid ✅" : "Pending ❌"));
            System.out.println("💰 Total Amount: $" + totalAmount);
            System.out.println("🔗 Thank you for shopping with us!");
            System.out.println("---------------------------------------------------");

        } catch (Exception e) {
            System.err.println("❌ Error generating email content: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int calculateTotalAmount(JSONObject orderInfo) {
        int productId = orderInfo.getJSONObject("orders").getInt("productId");
        int quantity = orderInfo.getJSONObject("orders").getInt("quantity");
        int pricePerUnit = 10 + (productId % 41); // Giả lập giá sản phẩm từ 10 - 50 USD
        return pricePerUnit * quantity;
    }
}
