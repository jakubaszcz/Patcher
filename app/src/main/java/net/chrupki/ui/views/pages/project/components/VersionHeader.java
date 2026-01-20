package net.chrupki.ui.views.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import net.chrupki.app.AppContext;

public class VersionHeader extends HBox {

    public VersionHeader() {

        Label title = new Label();
        title.textProperty().bind(AppContext.projectContext().getName());
        title.getStyleClass().add("version-title");

        Button addButton = new Button("+");
        addButton.getStyleClass().add("version-add-button");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        addButton.setOnAction(e -> {
            ProjectModel.setSwitchProjectModal(true);
            ProjectModel.setSwitchCreateVersionProjectModal(true);
        });

        setMaxWidth(Double.MAX_VALUE);

        getStyleClass().add("version-header");

        getChildren().addAll(
                title,
                spacer,
                addButton
        );
    }
}
