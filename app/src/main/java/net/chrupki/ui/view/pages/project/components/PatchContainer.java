package net.chrupki.ui.view.pages.project.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import net.chrupki.database.dao.TagDAO;
import net.chrupki.dto.TagDTO;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.dto.PatchDTO;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

public class PatchContainer extends HBox {

    public PatchContainer(PatchDTO patch) {

        Label typeLabel = new Label();
        StringProperty type = new SimpleStringProperty(
                GlobalModel.getTags().stream()
                        .filter(tag -> tag.getId() == patch.getTid())
                        .map(TagDTO::getName)
                        .findFirst()
                        .orElse("")
        );
        typeLabel.textProperty().bind(type);

        typeLabel.setAlignment(Pos.CENTER);
        typeLabel.setPadding(new Insets(4, 10, 4, 10));
        new Styles().apply(typeLabel, TextTheme.TEXT_BADGE);

        Label content = new Label(patch.getContent());
        content.getStyleClass().add("project-item-meta");
        content.setWrapText(true);

        VBox textBox = new VBox(4, typeLabel, content);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Button editButton = new Button("Edit");
        new Styles().apply(editButton, ButtonTheme.EDIT);

        editButton.setOnAction(e -> {
            HubModel.patchModel().from(patch);
            GlobalModel.setSwitchProjectModal(true);
            GlobalModel.setSwitchEditPatchProjectModal(true);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(12);

        new Styles().apply(this, ContainerTheme.BODY);


        getChildren().addAll(
                textBox,
                spacer,
                editButton
        );
    }
}
