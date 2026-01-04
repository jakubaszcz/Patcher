package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.ui.model.ProjectModel;

import java.util.Map;

public class VersionContainer extends VBox {

    public VersionContainer(ProjectModel model, String version, int id) {
        HBox hBox = new HBox(10);

        Label label = new Label(version);
        Button button = new Button("Select this version");

        button.setOnAction(e -> {
            AppData.setCurrentVersionId(id);

            model.getPatches().clear();

            try {
                model.getPatches().addAll(PatchDAO.findAll(AppData.getCurrentProjectName()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        hBox.getChildren().addAll(label, button);
        this.getChildren().add(hBox);
    }
}
