package net.chrupki.ui.views.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.ui.views.pages.project.dto.VersionDTO;

public class VersionContainer extends HBox {

    public VersionContainer(VersionDTO version) {

        // Labels (gauche)
        Label name = new Label(version.getVersion());
        name.getStyleClass().add("version-item-title");

        Label meta = new Label("Version");
        meta.getStyleClass().add("version-item-meta");

        VBox textBox = new VBox(2, name, meta);
        textBox.setAlignment(Pos.CENTER_LEFT);

        // Bouton Edit (droite)
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("version-item-button");

        // Spacer pour pousser le bouton à droite
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Layout principal
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        getStyleClass().add("version-item");

        getChildren().addAll(
                textBox,
                spacer,
                editButton
        );
    }
}
