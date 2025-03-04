package vn.edu.ueh.bit.pipes.process.read.credit;

import java.util.List;

import vn.edu.ueh.bit.pipes.core.CreditNote;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;

public class CreditNoteReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        List<CreditNote> creditNotes = message.getInvoiceInfo().getCreditNotes();
        System.out.println("📄 Reading Credit Notes: " + creditNotes);
        return message;
    }
}
