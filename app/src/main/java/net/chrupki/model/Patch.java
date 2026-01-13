package net.chrupki.model;

public class Patch {
    private final String content;
    private final String type;
    private final int id;
    private final int vid;

    public Patch(String content, String type, int id, int vid) {
        this.content = content;
        this.type = type;
        this.id = id;
        this.vid = vid;
    }

    public String getContent() {
        return content;
    }
    public String getType() {
        return type;
    }
    public int getId() { return id; }
    public int getVid() { return vid; }
}
