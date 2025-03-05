package vn.edu.ueh.bit.pipes.client;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class OrderConsumer {
    private static final String ORDER_QUEUE = "order_queue";  // Hàng đợi nhận đơn hàng
    private static final String STOCK_QUEUE = "stock_queue";  // Hàng đợi kiểm tra tồn kho

    public void start() {
        System.out.println("🔄 Waiting order from queue...");
        consumeOrders();
    }

    private void consumeOrders() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Khai báo queue nếu chưa tồn tại
            channel.queueDeclare(ORDER_QUEUE, true, false, false, null);
            channel.queueDeclare(STOCK_QUEUE, true, false, false, null);
            System.out.println("✅ Consumer sẵn sàng nhận đơn hàng!");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("📦 Nhận đơn hàng: " + message);

                // Gửi đơn hàng đến StockCheckerService qua stock_queue
                sendToStockQueue(channel, message);
            };

            channel.basicConsume(ORDER_QUEUE, true, deliverCallback, consumerTag -> {});

        } catch (IOException | TimeoutException e) {
            System.err.println("❌ ERROR Consumer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendToStockQueue(Channel channel, String message) throws IOException {
        channel.basicPublish("", STOCK_QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("📤 Đã gửi đơn hàng đến StockCheckerService: " + message);
    }
}
