package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;

import java.util.Map;

public class VersionContainer extends VBox {

    private enum Type {
        Patch,
        Add,
        Features,
        Fix
    }

    private static Map<Type, String> TYPES = Map.of(Type.Patch, "Patch", Type.Add, "Add", Type.Features, "Features", Type.Fix, "Fix");

    public VersionContainer(String version, int id) {
        HBox hBox = new HBox(10);

        Label label = new Label(version);
        Button button = new Button("Select this version");

        button.setOnAction(e -> AppData.setCurrentVersionId(id));

        hBox.getChildren().addAll(label, button);
        this.getChildren().add(hBox);
    }
}
