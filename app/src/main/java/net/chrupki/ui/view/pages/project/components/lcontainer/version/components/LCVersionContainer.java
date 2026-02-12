package net.chrupki.ui.view.pages.project.components.lcontainer.version.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.dto.VersionDTO;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.Consumer;

public class LCVersionContainer extends HBox {

    public LCVersionContainer(VersionDTO version, Consumer<VersionDTO> onSelectVersion) {

        Label name = new Label("v" + version.getVersion());
        new Styles().apply(name, TextTheme.TEXT_ITEM);

        Label type = new Label(version.getType());
        type.getStyleClass().add("version-type-badge");

        HBox versionBox = new HBox(8, name, type);

        Label meta = new Label("Version");
        new Styles().apply(meta, TextTheme.TEXT_MAIN);


        VBox textBox = new VBox(2, versionBox, meta);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("project-item-button");

        editButton.setOnAction(e -> {
            HubModel.versionModel().from(version);
            GlobalModel.setSwitchProjectModal(true);
            GlobalModel.setSwitchEditVersionProjectModal(true);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        getStyleClass().add("project-item");

        setOnMouseClicked(e -> {
            onSelectVersion.accept(version);
        });

        getChildren().addAll(
                textBox,
                spacer,
                editButton
        );
    }
}
