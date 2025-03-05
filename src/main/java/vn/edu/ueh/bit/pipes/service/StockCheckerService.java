package vn.edu.ueh.bit.pipes.service;

import com.rabbitmq.client.*;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockCheckerService {
    private static final String STOCK_QUEUE = "stock_queue"; // Hàng đợi nhận đơn hàng từ Producer
    private static final String STOCK_CHECKED_QUEUE = "stock_checked_queue"; // Hàng đợi gửi đơn hàng đã kiểm tra

    // Giả lập kho hàng
    private final Map<Integer, Integer> inventory = new ConcurrentHashMap<>();

    public StockCheckerService() {
        inventory.put(1, 500); // Giả lập có 500 sản phẩm ID = 1 trong kho
    }

    public void start() {
        System.out.println("🏪 StockCheckerService is running, waiting for orders...");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Khai báo hàng đợi
            channel.queueDeclare(STOCK_QUEUE, true, false, false, null);
            channel.queueDeclare(STOCK_CHECKED_QUEUE, true, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("📦 Received order from Producer: " + message);

                String updatedMessage = checkStock(message);
                if (updatedMessage != null) {
                    channel.basicPublish("", STOCK_CHECKED_QUEUE, null, updatedMessage.getBytes(StandardCharsets.UTF_8));
                    System.out.println("✅ Order has sufficient stock, forwarded to stock_checked_queue.");
                }
            };

            channel.basicConsume(STOCK_QUEUE, true, deliverCallback, consumerTag -> {});

        } catch (Exception e) {
            System.err.println("❌ Error in StockCheckerService: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String checkStock(String orderJson) {
        JSONObject orderObject = new JSONObject(orderJson);
        JSONObject orderInfo = orderObject.getJSONObject("order_info");

        // Lấy đơn hàng
        JSONObject orderItem = orderInfo.getJSONObject("orders");
        int productId = orderItem.getInt("productId");
        int quantity = orderItem.getInt("quantity");

        // Kiểm tra tồn kho
        if (!inventory.containsKey(productId) || inventory.get(productId) < quantity) {
            System.out.println("❌ Out of stock for product " + productId + "! Order rejected.");
            return null;
        }

        // Trừ số lượng tồn kho
        inventory.put(productId, inventory.get(productId) - quantity);
        System.out.println("✅ Inventory updated: Product " + productId + " remaining quantity: " + inventory.get(productId) + " units.");

        // Đánh dấu đã kiểm tra kho
        orderObject.put("stockChecked", true);
        return orderObject.toString();
    }
}
