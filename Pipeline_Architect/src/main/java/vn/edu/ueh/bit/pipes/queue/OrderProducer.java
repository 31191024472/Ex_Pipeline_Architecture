package vn.edu.ueh.bit.pipes.queue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vn.edu.ueh.bit.pipes.core.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
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
            String json = new String(Files.readAllBytes(Paths.get("src/main/java/vn/edu/ueh/bit/pipes/client/order_data.json")));
            Message order = objectMapper.readValue(json, Message.class);

            // Gửi vào RabbitMQ
            rabbitTemplate.convertAndSend("","orderQueue", objectMapper.writeValueAsString(order));
            System.out.println("📤 Đơn hàng đã gửi vào queue!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
