package vn.edu.ueh.bit.pipes.core.entities;

public class Note {
    private long id;
    private boolean settled;
    private String text;
    private long deliveryId;
    private String author;

    // Getters và Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public boolean isSettled() { return settled; }
    public void setSettled(boolean settled) { this.settled = settled; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public long getDeliveryId() { return deliveryId; }
    public void setDeliveryId(long deliveryId) { this.deliveryId = deliveryId; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
