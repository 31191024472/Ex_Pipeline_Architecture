package vn.edu.ueh.bit.pipes.service;
import vn.edu.ueh.bit.pipes.core.Invoice;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {
    // Giả lập kho hàng (50 sản phẩm với số lượng từ 50 - 500)
    private final Map<Long, Integer> stock = new HashMap<>();

    public InventoryService() {
        for (long i = 1; i <= 50; i++) {
            stock.put(i, (int) (Math.random() * 450) + 50);
        }
    }

    public boolean checkStock(List<Invoice> orders) {
        System.out.println("🔍 Kiểm tra tồn kho...");

        for (Invoice order : orders) {
            long productId = order.getProductId();
            int requiredQty = (int) order.getQuantity();

            if (!stock.containsKey(productId) || stock.get(productId) < requiredQty) {
                System.out.println("❌ Không đủ hàng: Sản phẩm ID " + productId);
                return false;
            }
        }
        System.out.println("✅ Đủ hàng, tiếp tục xử lý.");
        return true;
    }
}
