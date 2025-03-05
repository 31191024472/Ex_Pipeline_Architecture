package vn.edu.ueh.bit.pipes.core.entities;

import java.util.List;

public class InvoiceInfo {
    private long id;
    private List<Invoice> invoices;
    private List<Payment> payments;
    private List<CreditNote> creditNotes;

    // Getters và Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public List<Invoice> getInvoices() { return invoices; }
    public void setInvoices(List<Invoice> invoices) { this.invoices = invoices; }

    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }

    public List<CreditNote> getCreditNotes() { return creditNotes; }
    public void setCreditNotes(List<CreditNote> creditNotes) { this.creditNotes = creditNotes; }
}
