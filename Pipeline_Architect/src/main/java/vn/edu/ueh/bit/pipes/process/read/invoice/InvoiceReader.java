package vn.edu.ueh.bit.pipes.process.read.invoice;

import java.util.List;

import vn.edu.ueh.bit.pipes.core.entities.IMessage;
import vn.edu.ueh.bit.pipes.core.Invoice;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;

public class InvoiceReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        List<Invoice> invoices = message.getInvoiceInfo().getInvoices();
        System.out.println("📄 Reading Invoices: " + invoices);
        return message;
    }
}
