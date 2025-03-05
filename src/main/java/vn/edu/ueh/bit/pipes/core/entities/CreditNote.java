package vn.edu.ueh.bit.pipes.core.entities;

public class CreditNote {
    private long id;
    private String note;
    private boolean verification;

    // Getters và Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public boolean isVerification() { return verification; }
    public void setVerification(boolean verification) { this.verification = verification; }
}
