package net.chrupki.ui.view.pages.project.modals.export;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import net.chrupki.database.dao.TagDAO;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.HubModel;
import net.chrupki.request.ExportRequest;
import net.chrupki.ui.model.GlobalModel;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class ExportModal extends VBox {

    public ExportModal(Runnable onClose, Consumer<ExportRequest> onExport) {

        ObservableList<String> templates = GlobalModel.getTemplates();

        Label title = new Label("Export version");
        title.getStyleClass().add("modal-title");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("modal-combobox");
        comboBox.setPromptText("Select a template");

        boolean hasTemplates = templates != null && !templates.isEmpty();

        if (hasTemplates) {
            comboBox.setItems(templates);
            comboBox.getSelectionModel().selectFirst();
        }

        Button closeButton = new Button("Cancel");
        closeButton.getStyleClass().add("modal-button-close");

        Button createButton = new Button("Export");
        createButton.getStyleClass().add("modal-button-create");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(12, closeButton, spacer, createButton);
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        createButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showSaveDialog(getScene().getWindow());
            if (file == null) {
                return;
            }

            String template = hasTemplates
                    ? comboBox.getValue()
                    : "default";

            try {
                onExport.accept(new ExportRequest(
                        HubModel.projectModel().getName().get(),
                        VersionDAO.findNameById(
                                HubModel.versionModel().getId().get()
                        ),
                        HubModel.versionModel().getType().get(),
                        "markdown",
                        template,
                        List.of(),
                        TagDAO.all(),
                        file.toPath()
                ));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        closeButton.setOnAction(e -> onClose.run());

        setPrefWidth(360);
        setMaxWidth(360);

        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchExportModal());
        managedProperty().bind(GlobalModel.getSwitchExportModal());

        if (hasTemplates) {
            getChildren().addAll(title, comboBox, actions);
        } else {
            getChildren().addAll(title, actions);
        }
    }
}