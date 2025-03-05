package vn.edu.ueh.bit.pipes.core.entities;

public interface IMessage {
    InvoiceInfo getInvoiceInfo();
    void setInvoiceInfo(InvoiceInfo invoiceInfo);
    void setFileContent(String content);
    String getFileContent();
}
