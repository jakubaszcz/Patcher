package net.chrupki.ui.view.pages.project.modals.tags;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.dto.TagDTO;
import net.chrupki.model.HubModel;
import net.chrupki.request.PatchRequest;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.Consumer;

public class CreateTagModal extends VBox {

    public CreateTagModal(
            Consumer<TagDTO> onCreate,
            Runnable onClose
    ) {
        ObservableList<TagDTO> tags = GlobalModel.getTags();

        Label title = new Label("Create patch");
        new Styles().apply(title, TextTheme.SUBTITLE);


        TextField textField = new TextField();
        textField.setPromptText("Tag name");
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
            onCreate.accept(
                    new TagDTO(textField.getText(), -1)
            );
            onClose.run();
        });

        closeButton.setOnAction(e -> {
            onClose.run();
        });

        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchCreateTagProjectModal());
        managedProperty().bind(GlobalModel.getSwitchCreateTagProjectModal());

        getChildren().addAll(
                title,
                textField,
                actions
        );
    }

}
