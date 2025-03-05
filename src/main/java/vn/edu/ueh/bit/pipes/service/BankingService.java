package vn.edu.ueh.bit.pipes.service;

import com.rabbitmq.client.*;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BankingService {
    private static final String STOCK_CHECKED_QUEUE = "stock_checked_queue";
    private static final String PAYMENT_CHECKED_QUEUE = "payment_checked_queue";

    // Simulated bank account balances (custId -> account balance)
    private static final Map<Integer, Integer> accountBalances = new HashMap<>();

    static {
        Random rand = new Random();
        for (int i = 1; i <= 500; i++) {
            accountBalances.put(i, rand.nextInt(2000) + 500); // Random balance between 500 and 2500
        }
    }

    public void start() {
        System.out.println("🏦 BankingService is ready to process payments...");
        processPayments();
    }

    private void processPayments() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(STOCK_CHECKED_QUEUE, true, false, false, null);
            channel.queueDeclare(PAYMENT_CHECKED_QUEUE, true, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("💳 Received payment request: " + message);

                String updatedMessage = checkPayment(message);
                if (updatedMessage != null) {
                    channel.basicPublish("", PAYMENT_CHECKED_QUEUE, null, updatedMessage.getBytes(StandardCharsets.UTF_8));
                    System.out.println("✅ Payment processed successfully! Order forwarded to the next queue.");
                } else {
                    System.out.println("❌ Payment failed. Order rejected.");
                }
            };

            channel.basicConsume(STOCK_CHECKED_QUEUE, true, deliverCallback, consumerTag -> {});

        } catch (Exception e) {
            System.err.println("❌ ERROR in BankingService: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String checkPayment(String orderJson) {
        JSONObject orderObject = new JSONObject(orderJson);
        JSONObject orderInfo = orderObject.getJSONObject("order_info");

        int custId = orderInfo.getJSONObject("payments").getInt("custId");

        // Calculate total price
        int totalPrice = orderInfo.getJSONObject("orders").getInt("quantity") * 20; // Simulated price per unit = $20

        System.out.println("💰 Checking balance for customer " + custId + ": Total price = $" + totalPrice);

        if (!accountBalances.containsKey(custId)) {
            System.out.println("❌ Customer does not have a valid bank account! Order rejected.");
            return null;
        }

        int balance = accountBalances.get(custId);
        if (balance >= totalPrice) {
            accountBalances.put(custId, balance - totalPrice);
            System.out.println("✅ Payment successful! Remaining balance for customer " + custId + ": $" + accountBalances.get(custId));

            // Mark order as payment-checked
            orderObject.put("paymentChecked", true);
            return orderObject.toString();
        } else {
            System.out.println("❌ Insufficient funds! Order rejected.");
            return null;
        }
    }
}
