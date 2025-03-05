package vn.edu.ueh.bit.pipes.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

public class OrderProducerGenerator {
    private static final String QUEUE_NAME = "order_queue";
    private static final int PRODUCT_ID = 1; // Chỉ có 1 loại sản phẩm
    private static final int QUANTITY_PER_ORDER = 50; // Mỗi đơn hàng có 50 sản phẩm

    public void start() {
        System.out.println("🚀 Starting random order generation...");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            ObjectMapper objectMapper = new ObjectMapper();
            Random random = new Random();

            while (true) {
                // Tạo đơn hàng giả lập
                OrderData orderData = generateRandomOrder(random);
                String message = objectMapper.writeValueAsString(orderData);

                // Gửi vào RabbitMQ
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("✅ Order sent: " + message);

                // Nghỉ ngẫu nhiên từ 1-5 giây trước khi gửi tiếp
                Thread.sleep((random.nextInt(5) + 1) * 1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OrderData generateRandomOrder(Random random) {
        int orderId = random.nextInt(10000);
        int custId = random.nextInt(500) + 1;

        return new OrderData(
                new OrderInfo(
                        new Order(orderId, custId, PRODUCT_ID, QUANTITY_PER_ORDER, "Sản phẩm demo"),
                        new Delivery(
                                random.nextInt(100) + 1,
                                "Order Note",
                                "Random Address " + (random.nextInt(100) + 1),
                                random.nextBoolean()
                        ),
                        new Payment(
                                random.nextInt(10000),
                                custId,
                                UUID.randomUUID().toString().substring(0, 19),
                                String.valueOf(random.nextInt(900) + 100)
                        )
                )
        );
    }

    // Các lớp dữ liệu mô phỏng đơn hàng
    record OrderData(OrderInfo order_info) {}
    record OrderInfo(Order orders, Delivery delivery, Payment payments) {}
    record Order(int orderId, int custId, int productId, int quantity, String description) {}
    record Delivery(int nodeId, String note, String deliveryAdd, boolean isDelivery) {}
    record Payment(int paymentId, int custId, String cardNumber, String cvv) {}
}
