package vn.edu.ueh.bit.pipes.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceInfo {
    @JsonProperty("orders")
    private List<Invoice> invoices = new ArrayList<>();

    private List<CreditNote> creditNotes = new ArrayList<>();

    @JsonProperty("payments")
    private List<Payment> payments = new ArrayList<>();

    @JsonProperty("delivery")
    private List<Note> notes = new ArrayList<>();


    // Getter và Setter
    public void setInvoices(List<Invoice> invoices) { this.invoices = invoices; }
    public List<Invoice> getInvoices() { return invoices; }
    public void setCreditNotes(List<CreditNote> creditNotes) { this.creditNotes = creditNotes; }
    public List<CreditNote> getCreditNotes() { return creditNotes; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }
    public List<Payment> getPayments() { return payments; }
    public void setNotes(List<Note> notes) { this.notes = notes; }
    public List<Note> getNotes() { return notes; }

    // Tính tổng số tiền cần thanh toán từ danh sách hóa đơn
    public double getTotalAmount() {
        return invoices.stream()
                .mapToDouble(invoice -> invoice.getQuantity() * invoice.getPrice())
                .sum();
    }

    @Override
    public String toString() { return "InvoiceInfo"; }

    // Phương thức chuyển đối tượng thành JSON
    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);  // Sử dụng Jackson để chuyển đổi
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
