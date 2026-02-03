package net.chrupki.ui.view.pages.project.components.lcontainer.tag;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import net.chrupki.dto.TagDTO;
import javafx.scene.control.ScrollPane;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.view.pages.project.components.lcontainer.tag.components.LCTagContainer;
import net.chrupki.ui.view.pages.project.components.lcontainer.version.components.LCVersionContainer;

public class LCTag extends VBox {

    public LCTag() {
        ObservableList<TagDTO> tags = GlobalModel.getTags();

        refresh(tags);

        tags.addListener((ListChangeListener<TagDTO>) c ->
                refresh(tags)
        );
    }

    private void refresh(ObservableList<TagDTO> tags) {
        getChildren().clear();

        for (TagDTO t : tags) {
            getChildren().add(
                    new LCTagContainer(t)
            );
        }
    }

}
