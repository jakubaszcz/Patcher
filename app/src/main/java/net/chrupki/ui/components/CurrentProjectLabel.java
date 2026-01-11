package net.chrupki.ui.components;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;

public class CurrentProjectLabel extends VBox {

    private static final String BLANK = "No current project";

    private static final HBox view = new HBox();

    public CurrentProjectLabel() {
        Label label = new Label(BLANK);
        Button button = new Button("Edit project");

        StringProperty currentProject = AppData.getPropertyCurrentProjectName();

        view.visibleProperty().bind(currentProject.isNotNull().and(currentProject.isNotEmpty()));

        label.textProperty().bind(Bindings.when(view.visibleProperty()).then(currentProject).otherwise(AppData.getPropertyCurrentProjectName()));

        view.getChildren().addAll(label, button);

        this.getChildren().addAll(view);
    }
}