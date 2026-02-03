package net.chrupki.model.files;

import javafx.beans.property.*;
import net.chrupki.dto.PatchDTO;

public class PatchModel {
    private static final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);
    private static final ObjectProperty<Integer> vid = new SimpleObjectProperty<>(null);
    private static final ObjectProperty<Integer> tid = new SimpleObjectProperty<>(null);
    private static final StringProperty content = new SimpleStringProperty();

    public void from(PatchDTO patchDTO) {
        id.set(patchDTO.getId());
        vid.set(patchDTO.getVid());
        tid.set(patchDTO.getTid());
        content.set(patchDTO.getContent());
    }

    public void clear() {
        id.set(null);
        vid.set(null);
        tid.set(null);
        content.set(null);
    }

    public ObjectProperty<Integer> getId() {
        return id;
    }

    public ObjectProperty<Integer> getVid() {
        return vid;
    }

    public StringProperty getContent() {
        return content;
    }

    public ObjectProperty<Integer> getTid() {
        return tid;
    }
}
