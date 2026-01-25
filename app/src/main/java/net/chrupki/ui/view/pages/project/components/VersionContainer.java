package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.view.pages.project.dto.VersionDTO;

import java.util.function.Consumer;

public class VersionContainer extends HBox {

    public VersionContainer(VersionDTO version, Consumer<Integer> onSelectVersion) {

        // Labels (gauche)
        Label name = new Label("v" + version.getVersion());
        name.getStyleClass().add("project-item-title");

        Label type = new Label(version.getType());
        type.getStyleClass().add("version-type-badge");

        HBox versionBox = new HBox(8, name, type);

        Label meta = new Label("Version");
        meta.getStyleClass().add("project-item-meta");

        VBox textBox = new VBox(2, versionBox, meta);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("project-item-button");

        editButton.setOnAction(e -> {
            AppContext.versionContext().setId(version.getId());
            AppContext.versionContext().setType(version.getType());
            ProjectModel.setSwitchProjectModal(true);
            ProjectModel.setSwitchEditVersionProjectModal(true);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        getStyleClass().add("project-item");

        setOnMouseClicked(e -> {
            onSelectVersion.accept(version.getId());
            AppContext.versionContext().setType(version.getType());
        });

        getChildren().addAll(
                textBox,
                spacer,
                editButton
        );
    }
}
