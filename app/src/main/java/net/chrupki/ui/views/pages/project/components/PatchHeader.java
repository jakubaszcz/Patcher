package net.chrupki.ui.views.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import net.chrupki.app.AppContext;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.request.ExportRequest;
import net.chrupki.ui.model.ProjectModel;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class PatchHeader extends HBox {

    public PatchHeader(Consumer<ExportRequest> onExport) {
        Label title = new Label("Patches");
        title.getStyleClass().add("project-title");

        Button addButton = new Button("+");
        addButton.getStyleClass().add("project-add-button");

        Button exportButton = new Button("Export");
        exportButton.getStyleClass().add("project-add-button");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        addButton.setOnAction(e -> {
            ProjectModel.setSwitchProjectModal(true);
            ProjectModel.setSwitchCreatePatchProjectModal(true);
        });

        exportButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();

            File file = chooser.showSaveDialog(getScene().getWindow());
            if (file == null) {
                return;
            }

            try {
                onExport.accept(new ExportRequest(
                        AppContext.projectContext().getName().get(),
                        VersionDAO.findNameById(
                                AppContext.versionContext().getId().get()
                        ),
                        "markdown",
                        List.of(),
                        file.toPath()
                ));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        setMaxWidth(Double.MAX_VALUE);

        getStyleClass().add("project-header");

        getChildren().addAll(
                title,
                spacer,
                exportButton,
                addButton
        );
    }

}
