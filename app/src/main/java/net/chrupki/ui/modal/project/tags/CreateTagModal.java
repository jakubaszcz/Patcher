package net.chrupki.ui.modal.project.tags;

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
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;

import java.util.function.Consumer;

public class CreateTagModal extends ModalTemplate {

    public CreateTagModal(
            Consumer<TagDTO> onCreate,
            Runnable onClose
    ) {
        super("Create tag", onClose);

        TextField textField = new TextField();
        textField.setPromptText("Tag name");
        new Styles().apply(textField, TextFieldTheme.NORMAL);


        Button createButton = new Button("Create");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

        createButton.setOnAction(e -> {
            onCreate.accept(
                    new TagDTO(textField.getText(), -1)
            );
            onClose.run();
        });

        visibleProperty().bind(GlobalModel.getSwitchCreateTagProjectModal());
        managedProperty().bind(GlobalModel.getSwitchCreateTagProjectModal());

        getChildren().addAll(
                textField
        );

        addActions(createButton);
    }

}
