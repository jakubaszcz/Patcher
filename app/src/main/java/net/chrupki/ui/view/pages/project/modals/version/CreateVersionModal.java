package net.chrupki.ui.view.pages.project.modals.version;

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
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.BiConsumer;

public class CreateVersionModal extends VBox {

    public CreateVersionModal(
            BiConsumer<String, String> onCreate,
            Runnable onClose
    ) {
        Label title = new Label("Create version");
        new Styles().apply(title, TextTheme.SUBTITLE);


        TextField textField = new TextField();
        textField.setPromptText("Version name");
        textField.getStyleClass().add("modal-textfield");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("modal-combobox");

        comboBox.getItems().addAll(
                "Alpha", "Beta", "Pre-release", "HotFix", "Stable"
        );

        comboBox.setPromptText("Select a type");

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
            onCreate.accept(textField.getText(), comboBox.getValue());
            textField.clear();
            onClose.run();
        });

        closeButton.setOnAction(e -> {
            onClose.run();
        });

        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchCreateVersionProjectModal());
        managedProperty().bind(GlobalModel.getSwitchCreateVersionProjectModal());


        getChildren().addAll(
                title,
                textField,
                comboBox,
                actions
        );
    }

}
