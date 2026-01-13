package net.chrupki.app.context;

import javafx.beans.property.*;

public class PatchContext {
    private static final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);
    private static final ObjectProperty<Integer> vid = new SimpleObjectProperty<>(null);
    private static final StringProperty content = new SimpleStringProperty();
    private static final StringProperty patch = new SimpleStringProperty();

    public ObjectProperty<Integer> getId() {
        return id;
    }

    public ObjectProperty<Integer> getVid() {
        return vid;
    }

    public StringProperty getContent() {
        return content;
    }

    public StringProperty getPatch() {
        return patch;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setVid(int vid) {
        this.vid.set(vid);
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public void setPatch(String patch) {
        this.patch.set(patch);
    }
}
