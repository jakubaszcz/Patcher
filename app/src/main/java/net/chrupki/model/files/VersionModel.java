package net.chrupki.model.files;

import javafx.beans.property.*;
import net.chrupki.dto.VersionDTO;

public class VersionModel {
    private final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);
    private static final StringProperty name = new SimpleStringProperty();
    private static final StringProperty type = new SimpleStringProperty();

    public void from(VersionDTO versionDTO) {
        id.set(versionDTO.getId());
        name.set(versionDTO.getVersion());
        type.set(versionDTO.getType());
    }

    public void clear() {
        id.set(null);
        name.set(null);
        type.set(null);
    }

    public StringProperty getType() { return type; }
    public StringProperty getName() { return name; }
    public ObjectProperty<Integer> getId() {
        return id;
    }
}
