package vn.edu.ueh.bit.pipes.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Note {
    private long noteId;
    private String notes;
    private String deliveryAdd;
    private boolean delivery;

    public void setNoteId(long noteId) { this.noteId = noteId; }
    public long getNoteId() { return noteId; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getNotes() { return notes; }
    public void setDelivery(boolean delivery) { this.delivery = delivery; }
    public boolean isDelivery() { return delivery; }
    public void setDeliveryAdd(String deliveryAdd) { this.deliveryAdd = deliveryAdd; }
    public String getDeliveryAdd() { return deliveryAdd; }

    @Override
    public String toString() { return "Note: " + noteId; }
    public String toJson() { return "{ \"noteId\": " + noteId + " }"; }
}
