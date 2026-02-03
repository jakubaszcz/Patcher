package net.chrupki.dto;

public class PatchDTO {
    private final String content;
    private final int tid;
    private final int id;
    private final int vid;

    public PatchDTO(String content, int tid, int id, int vid) {
        this.content = content;
        this.tid = tid;
        this.id = id;
        this.vid = vid;
    }

    public String getContent() {
        return content;
    }
    public int getTid() {
        return tid;
    }
    public int getId() { return id; }
    public int getVid() { return vid; }
}
