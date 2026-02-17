package net.chrupki.ui.view.pages.project.components.lcontainer.tag.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.dto.TagDTO;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

public class LCTagContainer extends HBox {

    public LCTagContainer(TagDTO tagDTO) {
        Label name = new Label(tagDTO.getName());
        new Styles().apply(name, TextTheme.TEXT_ITEM);

        HBox versionBox = new HBox(8, name);

        Label meta = new Label("Tag");
        new Styles().apply(meta, TextTheme.TEXT_MAIN);

        VBox textBox = new VBox(2, versionBox, meta);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Button editButton = new Button("Edit");
        new Styles().apply(editButton, ButtonTheme.EDIT);

        editButton.setOnAction(e -> {
            HubModel.tagModel().from(tagDTO);
            GlobalModel.setSwitchProjectsModal(true);
            GlobalModel.setSwitchEditTagProjectModal(true);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        new Styles().apply(this, ContainerTheme.BODY);

        getChildren().addAll(
                textBox,
                spacer,
                editButton
        );
    }

}
