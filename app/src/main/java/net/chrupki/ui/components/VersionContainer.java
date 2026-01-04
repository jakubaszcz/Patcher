package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.ui.model.ProjectModel;

import java.util.Map;
import java.util.function.Consumer;

public class VersionContainer extends VBox {

    public VersionContainer(ProjectModel model, String version, int id, Consumer<Integer> onSelectVersion) {
        HBox hBox = new HBox(10);

        Label label = new Label(version);
        Button button = new Button("Select this version");

        button.setOnAction(e -> {
            onSelectVersion.accept(id);
        });

        hBox.getChildren().addAll(label, button);
        this.getChildren().add(hBox);
    }
}
