package vn.edu.ueh.bit.pipes.core;

import java.util.List;

public class InvoiceInfo {
    private List<Invoice> invoices;
    private List<CreditNote> creditNotes;
    private List<Payment> payments;
    private List<Note> notes;

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
    public String toJson() { return "{ \"invoices\": " + invoices + " }"; }
}
