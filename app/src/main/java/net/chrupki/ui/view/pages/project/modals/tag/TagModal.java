package net.chrupki.ui.view.pages.project.modals.tag;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import net.chrupki.dto.TagDTO;
import net.chrupki.dto.VersionDTO;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.view.pages.project.components.VersionContainer;

public class TagModal extends VBox {

    private final VBox list = new VBox();

    public TagModal() {

        ObservableList<TagDTO> tags = GlobalModel.getTags();

        refresh(tags);

        setPrefWidth(360);
        setMaxWidth(360);

        tags.addListener((ListChangeListener<TagDTO>) c ->
                refresh(tags)
        );


        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchTagModal());
        managedProperty().bind(GlobalModel.getSwitchTagModal());

        getChildren().addAll(list, new Label("sqdes"));

    }

    private void refresh(ObservableList<TagDTO> tags) {
        list.getChildren().clear();

        for (TagDTO t : tags) {
            list.getChildren().addAll(
                    new Label("lkjfkofd")
            );
        }
    }

}
