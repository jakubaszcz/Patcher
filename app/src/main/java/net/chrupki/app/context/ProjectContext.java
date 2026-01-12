package net.chrupki.app.context;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectContext {

    private static final StringProperty name = new SimpleStringProperty();

    public void setName(String set) {
        name.set(set);
    }

    public StringProperty getName() {
        return name;
    }
}
