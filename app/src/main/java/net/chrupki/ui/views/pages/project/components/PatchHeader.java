package net.chrupki.ui.views.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import net.chrupki.app.AppContext;
import net.chrupki.ui.model.ProjectModel;

public class PatchHeader extends HBox {

    public PatchHeader() {
        Label title = new Label("Patches");
        title.getStyleClass().add("project-title");

        Button addButton = new Button("+");
        addButton.getStyleClass().add("project-add-button");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        addButton.setOnAction(e -> {
            ProjectModel.setSwitchProjectModal(true);
            ProjectModel.setSwitchCreatePatchProjectModal(true);
        });

        setMaxWidth(Double.MAX_VALUE);

        getStyleClass().add("project-header");

        getChildren().addAll(
                title,
                spacer,
                addButton
        );
    }

}
