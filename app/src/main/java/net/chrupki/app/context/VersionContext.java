package net.chrupki.app.context;

import javafx.beans.property.*;
import net.chrupki.database.dao.VersionDAO;

public class VersionContext {
    private final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);
    private static final StringProperty name = new SimpleStringProperty();


    public void clearId() {
        this.id.set(null);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public ObjectProperty<Integer> getId() {
        return id;
    }
}
