package ch.laengu.entity;

public class TextMessage {
    private Long id;
    private String text;
    private boolean isValid;

    public TextMessage() {}

    public TextMessage(Long id, String text) {
        this.id = id;
        this.text = text;
        this.isValid = false;
    }

    public TextMessage(Long id, String text, boolean isValid) {
        this.id = id;
        this.text = text;
        this.isValid = isValid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "TextMessage [id=" + id + ", text=" + text + ", isValid=" + isValid + "]";
    }
}
