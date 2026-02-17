package net.chrupki.ui.view.pages.project.components.lcontainer.tag;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.dto.TagDTO;
import javafx.scene.control.ScrollPane;
import net.chrupki.ui.EmptyStateView;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.view.pages.project.components.lcontainer.tag.components.LCTagContainer;
import net.chrupki.ui.view.pages.project.components.lcontainer.version.components.LCVersionContainer;

public class LCTag extends VBox {

    public LCTag() {
        ObservableList<TagDTO> tags = GlobalModel.getTags();

        setFillWidth(true);

        refresh(tags);

        tags.addListener((ListChangeListener<TagDTO>) c ->
                refresh(tags)
        );
    }

    private void refresh(ObservableList<TagDTO> tags) {
        getChildren().clear();

        if (tags.isEmpty()) {
            EmptyStateView emptyView = new EmptyStateView(
                    "Tag",
                    "Click here to create your first tag",
                    () -> {
                        GlobalModel.setSwitchProjectsModal(true);
                        GlobalModel.setSwitchCreateTagProjectModal(true);
                    }
            );
            VBox.setVgrow(emptyView, Priority.ALWAYS);
            getChildren().add(emptyView);
            return;
        }

        for (int i = 0; i < tags.size(); i++) {
            getChildren().add(
                    new LCTagContainer(tags.get(i), i % 2 == 0)
            );
        }
    }

}
