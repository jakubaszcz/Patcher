package net.chrupki.ui.controllers.dtos;

public class EditPatch {
    private final Integer id;
    private final Integer vid;
    private final String content;
    private final String type;

    public EditPatch(Integer id, Integer vid, String content, String type) {
        this.id = id;
        this.vid = vid;
        this.content = content;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
