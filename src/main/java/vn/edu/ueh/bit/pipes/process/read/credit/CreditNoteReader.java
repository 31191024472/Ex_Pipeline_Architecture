package vn.edu.ueh.bit.pipes.process.read.credit;

import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

public class CreditNoteReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        return message;
    }
}
