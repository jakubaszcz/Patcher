package net.chrupki.ui.components;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.app.context.ProjectContext;
import net.chrupki.ui.model.ProjectModel;

public class CurrentProjectLabel extends VBox {

    private static final String BLANK = "No current project";

    private static final HBox view = new HBox();

    private ProjectModel model;

    public CurrentProjectLabel(ProjectModel model, Runnable onDelete) {
        this.model = model;

        Label label = new Label(BLANK);

        Button delete = new Button("delete");

        StringProperty currentProject = AppContext.projectContext().getName();

        view.visibleProperty().bind(currentProject.isNotNull().and(currentProject.isNotEmpty()));

        label.textProperty().bind(Bindings.when(view.visibleProperty()).then(currentProject).otherwise(currentProject));

        delete.setOnAction(e -> {
            onDelete.run();
        });

        view.getChildren().addAll(label, delete);

        this.getChildren().addAll(view);
    }
}