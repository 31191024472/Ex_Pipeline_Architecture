# Ex_Pipeline_Architecture

## 1️⃣ Giới thiệu dự án
Dự án **Ex_Pipeline_Architecture** là một hệ thống xử lý đơn hàng theo kiến trúc pipeline, sử dụng Java Spring Boot kết hợp với RabbitMQ để truyền tải thông điệp giữa các dịch vụ. Hệ thống mô phỏng quy trình xử lý đơn hàng tự động, từ kiểm tra hàng tồn, tính phí giao hàng, xác minh thanh toán, đến tạo đơn và gửi email thông báo.

## 2️⃣ Kết quả đạt được
✅ Xây dựng hệ thống xử lý đơn hàng theo luồng (pipeline) giúp phân tán tải và xử lý từng bước một cách hiệu quả.
✅ Kết nối RabbitMQ để truyền tải thông điệp giữa các dịch vụ một cách linh hoạt.
✅ Tích hợp dịch vụ giả lập để kiểm tra tồn kho, tính phí giao hàng, kiểm tra thanh toán.
✅ Hỗ trợ mô phỏng dịch vụ khách hàng để chọn hàng và tạo đơn.
✅ Xây dựng backend hoàn chỉnh và có khả năng mở rộng.

## 3️⃣ Hướng dẫn sử dụng

### Yêu cầu hệ thống
- **Java 17**
- **Gradle** (quản lý dependencies)
- **MongoDB** (lưu trữ dữ liệu đơn hàng)
- **RabbitMQ** (message queue để truyền thông tin giữa các dịch vụ)

### Cài đặt và chạy dự án
1️⃣ **Clone dự án**
```bash
git clone https://github.com/31191024472/Ex_Pipeline_Architecture.git
cd Ex_Pipeline_Architecture
```

2️⃣ **Cấu hình môi trường**
- Đảm bảo RabbitMQ đang chạy trên `localhost:5672`
- Cấu hình MongoDB trong `application.properties`

3️⃣ **Chạy hệ thống**
```bash
./gradlew bootRun
```

4️⃣ **Mô phỏng gửi đơn hàng**
```bash
curl -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{ "custId": 1, "productId": 101, "quantity": 2 }'
```

## 4️⃣ Công nghệ sử dụng và lý do
### 🛠️ **Backend**: **Java Spring Boot**
➡ **Lý do**: Hỗ trợ microservices mạnh mẽ, dễ mở rộng và bảo trì.

### 🔄 **Message Queue**: **RabbitMQ**
➡ **Lý do**: Cho phép xử lý đơn hàng theo pipeline mà không bị tắc nghẽn, giúp hệ thống chịu tải tốt.

### 🗄️ **Database**: **MongoDB**
➡ **Lý do**: Dữ liệu đơn hàng có cấu trúc linh hoạt, phù hợp với JSON và dễ dàng mở rộng.

### 🏗️ **Build Tool**: **Gradle**
➡ **Lý do**: Quản lý dependencies nhanh chóng và tối ưu hơn Maven.

## 5️⃣ Mở rộng và phát triển
📌 **Thêm UI cho khách hàng**: Xây dựng frontend bằng React để khách hàng có thể tạo đơn trực quan.
📌 **Tích hợp thêm dịch vụ vận chuyển thực tế**: Kết nối với API của các bên thứ ba để lấy phí giao hàng.
📌 **Nâng cao khả năng mở rộng**: Áp dụng Kubernetes để triển khai hệ thống trên cloud.

## 6️⃣ Đóng góp
Mọi ý kiến đóng góp hoặc cải tiến xin vui lòng tạo Pull Request hoặc liên hệ qua Issues của repo này.

🚀 **Cùng nhau xây dựng một hệ thống mạnh mẽ!** 🚀

