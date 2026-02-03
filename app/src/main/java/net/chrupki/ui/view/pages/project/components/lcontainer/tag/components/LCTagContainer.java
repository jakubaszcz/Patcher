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

public class LCTagContainer extends HBox {

    public LCTagContainer(TagDTO tagDTO) {
        Label name = new Label(tagDTO.getName());
        name.getStyleClass().add("project-item-title");

        HBox versionBox = new HBox(8, name);

        Label meta = new Label("Tag");
        meta.getStyleClass().add("project-item-meta");

        VBox textBox = new VBox(2, versionBox, meta);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("project-item-button");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        getStyleClass().add("project-item");

        getChildren().addAll(
                textBox,
                spacer,
                editButton
        );
    }

}
