package net.chrupki.ui.view.pages.project.dto;

public class PatchDTO {
    private final String content;
    private final String type;
    private final int id;
    private final int vid;

    public PatchDTO(String content, String type, int id, int vid) {
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
