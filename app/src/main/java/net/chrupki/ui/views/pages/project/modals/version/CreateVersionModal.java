package net.chrupki.ui.views.pages.project.modals.version;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class CreateVersionModal extends VBox {

    public CreateVersionModal(
            Consumer<String> onCreate,
            Runnable onClose
    ) {
        Label title = new Label("Create version");
        title.getStyleClass().add("modal-title");

        TextField textField = new TextField();
        textField.setPromptText("Version name");
        textField.getStyleClass().add("modal-textfield");

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
            onCreate.accept(textField.getText());
            textField.clear();
            onClose.run();
        });

        closeButton.setOnAction(e -> {
            onClose.run();
        });

        getStyleClass().add("modal-card");

        visibleProperty().bind(ProjectModel.getSwitchCreateVersionProjectModal());
        managedProperty().bind(ProjectModel.getSwitchCreateVersionProjectModal());


        getChildren().addAll(
                title,
                textField,
                actions
        );
    }

}
