package net.chrupki.ui.modal.project.version;

import javafx.event.Event;
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
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.*;

import java.util.function.BiConsumer;

public class CreateVersionModal extends ModalTemplate {

    public CreateVersionModal(
            BiConsumer<String, String> onCreate,
            Runnable onClose
    ) {
        super("Create version", onClose);

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

        comboBox.setPromptText("Select a type");

        Button createButton = new Button("Create");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

        createButton.setOnAction(e -> {
            String x = xField.getText();
            String y = yField.getText();
            String z = zField.getText();

            if (x.isBlank() || y.isBlank() || z.isBlank()) {
                GlobalModel.setErrorMessage("Version name must be in format x.y.z");
                return;
            }

            String versionName = "v" + x + "." + y + "." + z;

            if (comboBox.getValue() == null || comboBox.getValue().isBlank()) {
                GlobalModel.setErrorMessage("No type provided");
                return;
            }
            onCreate.accept(versionName, comboBox.getValue());
            xField.clear();
            yField.clear();
            zField.clear();
            comboBox.getSelectionModel().clearSelection();
            onClose.run();
        });

        visibleProperty().bind(GlobalModel.getSwitchCreateVersionProjectModal());
        managedProperty().bind(GlobalModel.getSwitchCreateVersionProjectModal());


        getChildren().addAll(
                versionContainer,
                comboBox
        );

        setOnMouseClicked(Event::consume);
        addActions(createButton);
    }

}
