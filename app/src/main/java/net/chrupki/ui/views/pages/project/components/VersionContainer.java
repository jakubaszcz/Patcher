package net.chrupki.ui.views.pages.project.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.chrupki.ui.views.pages.project.dto.VersionDTO;

public class VersionContainer extends VBox {

    public VersionContainer(VersionDTO version) {

        Label name = new Label(version.getVersion());
        name.getStyleClass().add("version-item-title");

        Label meta = new Label("Version");
        meta.getStyleClass().add("version-item-meta");

        setPadding(new Insets(10));
        setSpacing(4);

        getStyleClass().add("version-item");

        getChildren().addAll(name, meta);
    }
}
