package vn.edu.ueh.bit.pipes.core.entities;

public class Message {
    private String id;
    private String content;
    private String source;
    private InvoiceInfo invoiceInfo;

    // Getters và Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public InvoiceInfo getInvoiceInfo() { return invoiceInfo; }
    public void setInvoiceInfo(InvoiceInfo invoiceInfo) { this.invoiceInfo = invoiceInfo; }
}
