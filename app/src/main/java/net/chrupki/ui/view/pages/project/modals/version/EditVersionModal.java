package net.chrupki.ui.view.pages.project.modals.version;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.files.dtos.EditVersion;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.CardTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.Consumer;

public class EditVersionModal extends VBox {

    public EditVersionModal(
            Consumer<EditVersion> onSave,
            Consumer<Integer> onDelete,
            Runnable onClose
    ) {
        Label title = new Label("Edit version");
        new Styles().apply(title, TextTheme.SUBTITLE);


        TextField newName = new TextField();
        newName.setPromptText("New version name");
        new Styles().apply(newName, TextFieldTheme.NORMAL);


        Button deleteButton = new Button("Delete");
        new Styles().apply(deleteButton, ButtonTheme.DANGER);

        Button closeButton = new Button("Cancel");
        new Styles().apply(closeButton, ButtonTheme.CANCEL);

        Button saveButton = new Button("Save");
        new Styles().apply(saveButton, ButtonTheme.NORMAL);

        deleteButton.setOnAction(e -> {
            onDelete.accept(HubModel.versionModel().getId().get());
            onClose.run();
            newName.clear();
        });
        closeButton.setOnAction(e -> {
            onClose.run();
            newName.clear();
        });

        saveButton.setOnAction(e -> {
            if (!newName.getText().isBlank()) {
                onSave.accept(new EditVersion(
                        HubModel.versionModel().getId().get(),
                        newName.getText(),
                        HubModel.projectModel().getName().get()));
            }
            newName.clear();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(
                12,
                deleteButton,
                spacer,
                closeButton,
                saveButton
        );
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        new Styles().apply(this, CardTheme.NORMAL);

        visibleProperty().bind(GlobalModel.getSwitchEditVersionProjectModal());
        managedProperty().bind(GlobalModel.getSwitchEditVersionProjectModal());

        getChildren().addAll(
                title,
                newName,
                actions
        );
    }
}