package net.chrupki.ui.modal.project.version;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.files.dtos.EditVersion;
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ComboBoxTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.Consumer;

public class EditVersionModal extends ModalTemplate {

    public EditVersionModal(
            Consumer<EditVersion> onSave,
            Runnable onClose
    ) {
        super("Edit version", onClose);
        titleLabel.textProperty().bind(Bindings.concat("Edit version : ", HubModel.versionModel().getName()));

        HBox versionContainer = new HBox();
        versionContainer.setAlignment(Pos.CENTER_LEFT);
        versionContainer.setSpacing(5);

        Label vLabel = new Label("v");
        new Styles().apply(vLabel, TextTheme.TEXT_MAIN);

        TextField xField = new TextField();
        xField.setPrefWidth(50);
        xField.setPromptText("x");
        new Styles().apply(xField, TextFieldTheme.NORMAL);

        Label dot1 = new Label(".");
        new Styles().apply(dot1, TextTheme.TEXT_MAIN);

        TextField yField = new TextField();
        yField.setPrefWidth(50);
        yField.setPromptText("y");
        new Styles().apply(yField, TextFieldTheme.NORMAL);

        Label dot2 = new Label(".");
        new Styles().apply(dot2, TextTheme.TEXT_MAIN);

        TextField zField = new TextField();
        zField.setPrefWidth(50);
        zField.setPromptText("z");
        new Styles().apply(zField, TextFieldTheme.NORMAL);

        versionContainer.getChildren().addAll(vLabel, xField, dot1, yField, dot2, zField);

        ComboBox<String> comboBox = new ComboBox<>();
        new Styles().apply(comboBox, ComboBoxTheme.NORMAL);


        comboBox.getItems().addAll(
                "Alpha", "Beta", "Pre-release", "HotFix", "Stable"
        );

        Button saveButton = new Button("Save");
        new Styles().apply(saveButton, ButtonTheme.NORMAL);

        saveButton.setOnAction(e -> {
            String x = xField.getText();
            String y = yField.getText();
            String z = zField.getText();

            if (!x.isBlank() && !y.isBlank() && !z.isBlank()) {
                String versionName = "v" + x + "." + y + "." + z;
                onSave.accept(new EditVersion(
                        HubModel.versionModel().getId().get(),
                        versionName,
                        HubModel.projectModel().getName().get()));
            }
            xField.clear();
            yField.clear();
            zField.clear();
            comboBox.getSelectionModel().clearSelection();
            onClose.run();
        });

        visibleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                String currentName = HubModel.versionModel().getName().get();
                if (currentName != null && currentName.startsWith("v") && currentName.contains(".")) {
                    String[] parts = currentName.substring(1).split("\\.");
                    if (parts.length >= 1) xField.setText(parts[0]);
                    if (parts.length >= 2) yField.setText(parts[1]);
                    if (parts.length >= 3) zField.setText(parts[2]);
                } else {
                    xField.clear();
                    yField.clear();
                    zField.clear();
                }
            } else {
                xField.clear();
                yField.clear();
                zField.clear();
            }
        });

        HubModel.versionModel().getName().addListener((obs, oldVal, newVal) -> {
            if (isVisible()) {
                if (newVal != null && newVal.startsWith("v") && newVal.contains(".")) {
                    String[] parts = newVal.substring(1).split("\\.");
                    if (parts.length >= 1) xField.setText(parts[0]);
                    if (parts.length >= 2) yField.setText(parts[1]);
                    if (parts.length >= 3) zField.setText(parts[2]);
                } else {
                    xField.clear();
                    yField.clear();
                    zField.clear();
                }
            }
        });

        visibleProperty().bind(GlobalModel.getSwitchEditVersionProjectModal());
        managedProperty().bind(GlobalModel.getSwitchEditVersionProjectModal());

        getChildren().addAll(
                versionContainer,
                comboBox
        );

        setOnMouseClicked(e -> e.consume());
        addActions(saveButton);
    }
}