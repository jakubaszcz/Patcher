package net.chrupki.model.files;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.chrupki.dto.TagDTO;
import net.chrupki.dto.VersionDTO;

public class TagModel {

    private final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);
    private static final StringProperty name = new SimpleStringProperty();

    public void from(TagDTO tagDTO) {
        id.set(tagDTO.getId());
        name.set(tagDTO.getName());
    }

    public void clear() {
        id.set(null);
        name.set(null);
    }

    public StringProperty getName() { return name; }
    public ObjectProperty<Integer> getId() {
        return id;
    }

}
