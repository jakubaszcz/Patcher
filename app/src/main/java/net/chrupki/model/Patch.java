package net.chrupki.model;

public class Patch {
    private String content;
    private String type;

    public Patch(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }
    public String getType() {
        return type;
    }
}
