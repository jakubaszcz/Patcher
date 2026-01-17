package net.chrupki.ui.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class ProjectSelector extends VBox {

    private ProjectModel model;

    public ProjectSelector(ProjectModel projectModel, Consumer<String> onProjectSelected) {
        this.model = projectModel;
    }
}
