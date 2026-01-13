package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class VersionContainer extends VBox {

    public VersionContainer(ProjectModel model, String version, int id, Consumer<Integer> onSelectVersion, Consumer<Integer> onEditVersion) {
        HBox hBox = new HBox();

        Label label = new Label(version);
        Button select = new Button("Select this version");

        Button edit = new Button("Edit");

        select.setOnAction(e -> {
            onSelectVersion.accept(id);
        });

        edit.setOnAction(e -> {
            AppContext.versionContext().setId(id);
            onEditVersion.accept(id);
        });

        hBox.getChildren().addAll(label, select, edit);
        this.getChildren().add(hBox);
    }
}
