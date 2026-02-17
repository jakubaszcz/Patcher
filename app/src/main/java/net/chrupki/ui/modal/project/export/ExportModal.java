package net.chrupki.ui.modal.project.export;

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
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;

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

        boolean hasTemplates = templates != null && !templates.isEmpty();

        if (hasTemplates) {
            comboBox.setItems(templates);
            comboBox.getSelectionModel().selectFirst();
        }

        Button createButton = new Button("Export");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

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

        visibleProperty().bind(GlobalModel.getSwitchExportModal());
        managedProperty().bind(GlobalModel.getSwitchExportModal());

        if (hasTemplates) {
            getChildren().add(comboBox);
        }

        addActions(createButton);
    }
}