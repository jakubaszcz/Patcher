package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import net.chrupki.app.AppContext;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.view.pages.project.dto.PatchDTO;

public class PatchContainer extends HBox {

    public PatchContainer(PatchDTO patch) {

        Label typeLabel = new Label(patch.getType());
        typeLabel.getStyleClass().add("project-item-patch-type");

        typeLabel.setAlignment(Pos.CENTER);
        typeLabel.setPadding(new Insets(4, 10, 4, 10));
        typeLabel.getStyleClass().add("project-item-patch-type-box");

        Label content = new Label(patch.getContent());
        content.getStyleClass().add("project-item-meta");
        content.setWrapText(true);

        VBox textBox = new VBox(4, typeLabel, content);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("project-item-button");

        editButton.setOnAction(e -> {
            AppContext.patchContext().setId(patch.getId());
            AppContext.patchContext().setVid(patch.getVid());
            ProjectModel.setSwitchProjectModal(true);
            ProjectModel.setSwitchEditPatchProjectModal(true);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(12);

        getStyleClass().add("project-item");

        getChildren().addAll(
                textBox,
                spacer,
                editButton
        );
    }
}
