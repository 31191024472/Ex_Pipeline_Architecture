package vn.edu.ueh.bit.pipes.queue;
import vn.edu.ueh.bit.pipes.core.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class OrderProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendOrder() {
        try {
            // Đọc nội dung file order_data.json
            String json = new String(Files.readAllBytes(
                    Paths.get(getClass().getClassLoader().getResource("order_data.json").toURI())));

            System.out.println("📥 Dữ liệu JSON đọc được từ file: " + json);

            Message message = objectMapper.readValue(json, Message.class);
            System.out.println("📥 Dữ liệu Message sau khi chuyển đổi: " + message);

            // Gửi vào RabbitMQ
            rabbitTemplate.convertAndSend("orderQueue", objectMapper.writeValueAsString(message));
            System.out.println("📤 Đơn hàng đã gửi vào queue!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
