package net.chrupki.app.context;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class VersionContext {
    private final IntegerProperty id = new SimpleIntegerProperty();

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getIdProperty() {
        return id;
    }
}
