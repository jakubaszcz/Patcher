package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;

public class VersionContainer extends VBox {
    public VersionContainer(String version, int id) {
        HBox hBox = new HBox(10);

        Label label = new Label(version);
        Button button = new Button("Select this version");

        button.setOnAction(e -> System.out.println(id));

        hBox.getChildren().addAll(label, button);
        this.getChildren().add(hBox);
    }
}
