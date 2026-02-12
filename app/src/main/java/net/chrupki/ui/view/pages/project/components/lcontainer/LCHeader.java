package net.chrupki.ui.view.pages.project.components.lcontainer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.Consumer;

public class LCHeader extends HBox {

    private boolean isVersion = true;


    public LCHeader(
            Consumer<String> onToggle
    ) {
        Label title = new Label();
        title.textProperty().bind(HubModel.projectModel().getName());
        new Styles().apply(title, TextTheme.SUBTITLE);


        Button addButton = new Button("+");
        new Styles().apply(addButton, ButtonTheme.NORMAL);

        Button toggle = new Button("Tags");

        toggle.setOnAction(e -> {
            isVersion = !isVersion;

            String view = isVersion ? "version" : "tag";
            toggle.setText(isVersion ? "Tags" : "Versions");

            onToggle.accept(view);
        });
        new Styles().apply(toggle, ButtonTheme.NORMAL);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        addButton.setOnAction(e -> {

            GlobalModel.setSwitchProjectModal(true);

            if (isVersion) {
                GlobalModel.setSwitchCreateVersionProjectModal(true);
                GlobalModel.setSwitchCreateTagProjectModal(false);

            } else {
                GlobalModel.setSwitchCreateTagProjectModal(true);
                GlobalModel.setSwitchCreateVersionProjectModal(false);
            }

        });

        setMaxWidth(Double.MAX_VALUE);

        new Styles().apply(this, ContainerTheme.HEADER);

        getChildren().addAll(
                title,
                spacer,
                toggle,
                addButton
        );
    }

}
