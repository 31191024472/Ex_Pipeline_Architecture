package vn.edu.ueh.bit.pipes.service;

import vn.edu.ueh.bit.pipes.core.Note;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeliveryService {
    // Giả lập danh sách 100 khu vực giao hàng
    private final Map<Integer, Double> deliveryZones = new HashMap<>();

    public DeliveryService() {
        for (int i = 1; i <= 100; i++) {
            deliveryZones.put(i, Math.random() * 45 + 5);  // Phí từ 5 - 50 USD
        }
    }

    public boolean checkDelivery(Note deliveryInfo) {
        System.out.println("🚚 Kiểm tra dịch vụ giao hàng...");

        int nodeId = (int) deliveryInfo.getNoteId();

        if (!deliveryZones.containsKey(nodeId)) {
            System.out.println("❌ Không hỗ trợ giao hàng tại khu vực ID " + nodeId);
            return false;
        }

        double fee = deliveryZones.get(nodeId);
        System.out.println("✅ Dịch vụ khả dụng. Phí giao hàng: $" + fee);
        return true;
    }
}
