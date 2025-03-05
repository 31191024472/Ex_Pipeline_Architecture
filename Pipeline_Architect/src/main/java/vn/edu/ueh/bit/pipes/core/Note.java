package vn.edu.ueh.bit.pipes.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Note {
    @JsonProperty("noteId")
    private long noteId;

    @JsonProperty("note")
    private String notes;

    @JsonProperty("deliveryAdd")
    private String deliveryAdd;

    @JsonProperty("isDelivery")
    private boolean delivery;

    // Getter và Setter
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

    // Phương thức chuyển đối tượng thành JSON
    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);  // Sử dụng Jackson để chuyển đổi
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
