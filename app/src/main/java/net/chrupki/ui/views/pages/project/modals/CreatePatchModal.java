package net.chrupki.ui.views.pages.project.modals;

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
import net.chrupki.app.AppContext;
import net.chrupki.request.PatchRequest;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class CreatePatchModal extends VBox {

    public CreatePatchModal(
            Consumer<PatchRequest> onCreate,
            Runnable onClose
    ) {
        Label title = new Label("Create patch");
        title.getStyleClass().add("modal-title");

        TextField textField = new TextField();
        textField.setPromptText("Patch name");
        textField.getStyleClass().add("modal-textfield");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("modal-combobox");

        comboBox.getItems().addAll(
                "Patch", "Add", "Features", "Fix"
        );

        Button closeButton = new Button("Cancel");
        closeButton.getStyleClass().add("modal-button-close");

        Button createButton = new Button("Create");
        createButton.getStyleClass().add("modal-button-create");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(12, closeButton, spacer, createButton);
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        createButton.setOnAction(e -> {
            onCreate.accept(
                    new PatchRequest(textField.getText(), comboBox.getValue(), AppContext.versionContext().getId().get())
            );
            onClose.run();
        });

        closeButton.setOnAction(e -> {
            onClose.run();
        });

        getStyleClass().add("modal-card");

        visibleProperty().bind(ProjectModel.getSwitchCreatePatchProjectModal());
        managedProperty().bind(ProjectModel.getSwitchCreatePatchProjectModal());


        getChildren().addAll(
                title,
                textField,
                comboBox,
                actions
        );
    }

}
