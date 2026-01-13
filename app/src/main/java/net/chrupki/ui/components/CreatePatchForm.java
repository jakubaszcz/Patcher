package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import net.chrupki.app.AppContext;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.Patch;
import net.chrupki.request.ExportRequest;
import net.chrupki.request.PatchRequest;
import net.chrupki.ui.model.ProjectModel;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;


public class CreatePatchForm extends VBox {

    public CreatePatchForm(ProjectModel model, Consumer<PatchRequest> onCreatePatch, Consumer<ExportRequest> onExportPatch) {
        HBox box = new HBox();

        TextField textField = new TextField();

        Button createButton = new Button("Create patch");
        ComboBox<String> comboBoxExport = new ComboBox<>();
        Button exportButton = new Button("Export");

        ComboBox<String> comboBoxPatch = new ComboBox<>();

        comboBoxPatch.getItems().addAll(
                "Patch", "Add", "Features", "Fix"
        );

        comboBoxExport.getItems().addAll(
                "markdown"
        );

        comboBoxPatch.getSelectionModel().selectFirst();
        comboBoxExport.getSelectionModel().selectFirst();

        createButton.setOnAction(e -> {
            PatchRequest request = new PatchRequest(
                    textField.getText(),
                    comboBoxPatch.getValue(),
                    AppContext.versionContext().getId().get()
            );

            onCreatePatch.accept(request);

            textField.clear();
            comboBoxPatch.getSelectionModel().selectFirst();
        });

        exportButton.setOnAction(e -> {

            FileChooser chooser = new FileChooser();

            File file = chooser.showSaveDialog(getScene().getWindow());
            if (file == null) {
                return;
            }

            try {
                onExportPatch.accept(new ExportRequest(
                        AppContext.projectContext().getName().get(),
                        VersionDAO.findNameById(
                                AppContext.projectContext().getName().get(),
                                AppContext.versionContext().getId().get()
                        ),
                        comboBoxExport.getValue(),
                        List.of(),
                        file.toPath()
                ));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        box.getChildren().addAll(textField, comboBoxPatch, createButton, comboBoxExport, exportButton);

        this.getChildren().add(box);
    }
}
