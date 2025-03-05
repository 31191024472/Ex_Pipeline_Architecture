package vn.edu.ueh.bit.pipes.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    @JsonProperty("order_info")
    private InvoiceInfo invoiceInfo;

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "invoiceInfo=" + invoiceInfo +
                '}';
    }

}
