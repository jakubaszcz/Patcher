package net.chrupki.app.context;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class VersionContext {
    private final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);

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
