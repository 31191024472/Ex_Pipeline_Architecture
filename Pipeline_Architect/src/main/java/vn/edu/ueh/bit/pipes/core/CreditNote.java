package vn.edu.ueh.bit.pipes.core;

public class CreditNote {
    private long creditNoteId;
    private String notes;
    private boolean cancellation;

    public void setCreditNote(long creditNoteId) { this.creditNoteId = creditNoteId; }
    public long getCreditNote() { return creditNoteId; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getNotes() { return notes; }
    public void setCancellation(boolean cancellation) { this.cancellation = cancellation; }
    public boolean isCancellation() { return cancellation; }

    @Override
    public String toString() { return "CreditNote: " + creditNoteId; }
    public String toJson() { return "{ \"creditNoteId\": " + creditNoteId + " }"; }
}
