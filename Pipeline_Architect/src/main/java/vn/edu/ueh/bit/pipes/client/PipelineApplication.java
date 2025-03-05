package vn.edu.ueh.bit.pipes.client;

import org.springframework.context.annotation.ComponentScan;
import vn.edu.ueh.bit.pipes.queue.OrderProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ComponentScan(basePackages = "vn.edu.ueh.bit.pipes")
public class PipelineApplication {
    public static void main(String[] args) {
        SpringApplication.run(PipelineApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            // Lấy bean producer từ Spring Context và gửi đơn hàng vào queue
            OrderProducer producer = ctx.getBean(OrderProducer.class);
            producer.sendOrder();
        };
    }
}