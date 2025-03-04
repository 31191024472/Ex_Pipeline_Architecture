package vn.edu.ueh.bit.pipes.process.read.payment;

import java.util.List;

import vn.edu.ueh.bit.pipes.core.entities.IMessage;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.Payment;


public class PaymentReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        List<Payment> payments = message.getInvoiceInfo().getPayments();
        System.out.println("📄 Reading Payments: " + payments);
        return message;
    }
}
