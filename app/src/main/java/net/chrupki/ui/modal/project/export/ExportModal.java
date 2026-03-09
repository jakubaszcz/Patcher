package net.chrupki.ui.modal.project.export;

import javafx.collections.ObservableList;
import javafx.event.Event;
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
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class ExportModal extends ModalTemplate {

    public ExportModal(Runnable onClose, Consumer<ExportRequest> onExport) {
        super("Export version", onClose);

        ObservableList<String> templates = GlobalModel.getTemplates();

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("modal-combobox");
        comboBox.setPromptText("Select a template");
        comboBox.setItems(templates);

        Label noTemplatesLabel = new Label("No templates available, but don't worry, you can always create your own ! By now, you will have a default template.");
        new Styles().apply(noTemplatesLabel, TextTheme.TEXT_MAIN);
        noTemplatesLabel.setAlignment(Pos.CENTER);
        noTemplatesLabel.setWrapText(true);
        noTemplatesLabel.getStyleClass().add("modal-label");

        // Update visibility and children based on templates presence
        templates.addListener((javafx.collections.ListChangeListener<String>) c -> {
            boolean has = !templates.isEmpty();
            if (has) {
                if (!getChildren().contains(comboBox)) {
                    getChildren().add(comboBox);
                    getChildren().remove(noTemplatesLabel);
                }
                if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
                    comboBox.getSelectionModel().selectFirst();
                }
            } else {
                if (!getChildren().contains(noTemplatesLabel)) {
                    getChildren().add(noTemplatesLabel);
                    getChildren().remove(comboBox);
                }
            }
        });

        // Initial state
        if (!templates.isEmpty()) {
            getChildren().add(comboBox);
            comboBox.getSelectionModel().selectFirst();
        } else {
            getChildren().add(noTemplatesLabel);
        }

        Button createButton = new Button("Export");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

        createButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showSaveDialog(getScene().getWindow());
            if (file == null) {
                return;
            }

            String template = !templates.isEmpty()
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
                onClose.run();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        visibleProperty().bind(GlobalModel.getSwitchExportModal());
        managedProperty().bind(GlobalModel.getSwitchExportModal());

        setOnMouseClicked(Event::consume);
        addActions(createButton);
    }
}