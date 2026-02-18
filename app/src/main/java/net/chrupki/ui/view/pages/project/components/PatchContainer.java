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
import net.chrupki.ui.modal.ConfirmModal;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.dto.PatchDTO;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

public class PatchContainer extends HBox {

    public PatchContainer(PatchDTO patch, boolean pair) {

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
        new Styles().apply(content, TextTheme.TEXT_ITEM);
        content.setWrapText(true);

        VBox textBox = new VBox(4, typeLabel, content);
        textBox.setAlignment(Pos.CENTER_LEFT);

        HBox horizontal = new HBox();

        Button edit = new Button("Edit");
        new Styles().apply(edit, ButtonTheme.EDIT);

        Button delete = new Button("Delete");
        new Styles().apply(delete, ButtonTheme.EDIT);

        horizontal.getChildren().addAll(edit, delete);

        edit.setOnAction(e -> {
            HubModel.patchModel().from(patch);
            GlobalModel.setSwitchProjectsModal(true);
            GlobalModel.setSwitchEditPatchProjectModal(true);
        });

        delete.setOnAction(e -> {
            HubModel.patchModel().from(patch);
            ConfirmModal.changeType(ConfirmModal.Type.Patch);
            GlobalModel.setSwitchProjectsModal(true);
            GlobalModel.setSwitchConfirmModal(true);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(12);

        if (pair) new Styles().apply(this, ContainerTheme.BODY_PAIR);
        else new Styles().apply(this, ContainerTheme.BODY);

        getChildren().addAll(
                textBox,
                spacer,
                horizontal
        );
    }
}
