package net.chrupki.ui.controllers.files.dtos;

public class EditPatch {
    private final Integer id;
    private final Integer vid;
    private final Integer tid;
    private final String content;

    public EditPatch(Integer id, Integer vid, String content, Integer tid) {
        this.id = id;
        this.vid = vid;
        this.content = content;
        this.tid = tid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVid() {
        return vid;
    }

    public String getContent() {
        return content;
    }

    public Integer getTid() {
        return tid;
    }
}
