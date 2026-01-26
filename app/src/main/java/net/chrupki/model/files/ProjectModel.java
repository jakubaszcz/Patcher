package net.chrupki.model.files;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectModel {

    private static final StringProperty name = new SimpleStringProperty();

    public void setName(String set) {
        name.set(set);
    }

    public StringProperty getName() {
        return name;
    }
}
