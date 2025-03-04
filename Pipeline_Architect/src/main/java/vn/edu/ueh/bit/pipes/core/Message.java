package vn.edu.ueh.bit.pipes.core;

public class Message {
    private String fileContent;
    private InvoiceInfo invoiceInfo;

    public void setFileContent(String fileContent) { this.fileContent = fileContent; }
    public String getFileContent() { return fileContent; }
    public void setInvoiceInfo(InvoiceInfo invoiceInfo) { this.invoiceInfo = invoiceInfo; }
    public InvoiceInfo getInvoiceInfo() { return invoiceInfo; }

    @Override
    public String toString() { return "Message"; }
    public String toJson() { return "{ \"fileContent\": \"" + fileContent + "\" }"; }
}
