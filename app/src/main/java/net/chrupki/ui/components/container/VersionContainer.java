package net.chrupki.ui.components.container;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VersionContainer extends VBox {
    public VersionContainer(String version, int id) {
        HBox hBox = new HBox(10);

        Label label = new Label(version);
        Label idLabel = new Label(String.valueOf(id));
        Button button = new Button("Select this version");

        hBox.getChildren().addAll(idLabel, label, button);
        this.getChildren().add(hBox);
    }
}
