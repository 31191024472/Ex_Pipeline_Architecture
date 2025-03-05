package vn.edu.ueh.bit.pipes.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

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

    @Override
    public String toString() { return "InvoiceInfo"; }
    public String toJson() { return "{ \"invoices\": " + invoices + " }"; }
}
