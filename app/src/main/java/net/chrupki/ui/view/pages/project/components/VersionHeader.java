package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;

public class VersionHeader extends HBox {

    public VersionHeader() {

        Label title = new Label();
        title.textProperty().bind(HubModel.projectModel().getName());
        title.getStyleClass().add("project-title");

        Button addButton = new Button("+");
        addButton.getStyleClass().add("project-add-button");

        Button tagButton = new Button("Tags");
        tagButton.getStyleClass().add("project-add-button");


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        addButton.setOnAction(e -> {
            GlobalModel.setSwitchProjectModal(true);
            GlobalModel.setSwitchCreateVersionProjectModal(true);
        });

        tagButton.setOnAction(e -> {
            System.out.println(HubModel.projectModel().getName());
            GlobalModel.setSwitchProjectModal(true);
            GlobalModel.setSwitchTagModal(true);
        });

        setMaxWidth(Double.MAX_VALUE);

        getStyleClass().add("project-header");

        getChildren().addAll(
                title,
                spacer,
                tagButton,
                addButton
        );
    }
}
