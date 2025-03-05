package vn.edu.ueh.bit.pipes.client;

import vn.edu.ueh.bit.pipes.service.BankingService;
import vn.edu.ueh.bit.pipes.service.StockCheckerService;
import vn.edu.ueh.bit.pipes.service.InvoiceService;
import vn.edu.ueh.bit.pipes.service.EmailService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Khoi dong he thong xu ly don hang...");

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        // Khởi chạy các service trong các luồng riêng biệt
        executorService.submit(() -> new OrderProducerGenerator().start());
        executorService.submit(() -> new OrderConsumer().start());
        executorService.submit(() -> new StockCheckerService().start());
        executorService.submit(() -> new BankingService().start());
        executorService.submit(() -> new InvoiceService().start());
        executorService.submit(() -> new EmailService().start());

        System.out.println("✅ He thong da san sang xu ly don hang!");
    }
}
