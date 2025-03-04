package vn.edu.ueh.bit.pipes.process.read.note;

import java.util.List;

import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;
import vn.edu.ueh.bit.pipes.core.Note;

public class NoteReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        List<Note> notes = message.getInvoiceInfo().getNotes();
        System.out.println("📄 Reading Notes: " + notes);
        return message;
    }
}
