package net.chrupki.ui.view.pages.project.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.EmptyStateView;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.dto.PatchDTO;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.scroll.ScrollStyle;
import net.chrupki.ui.styles.theme.ContainerTheme;

public class Patch extends VBox {

    private final VBox list = new VBox();
    private final ScrollPane scrollPane = new ScrollPane();

    public Patch() {

        ObservableList<PatchDTO> patches = GlobalModel.getPatches();

        scrollPane.visibleProperty().bind(HubModel.versionModel().getId().isNotNull());
        scrollPane.managedProperty().bind(scrollPane.visibleProperty());

        scrollPane.setContent(list);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().add("invisible-scroll");
        new ScrollStyle().apply(scrollPane, true);
        scrollPane.setPannable(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        list.setFillWidth(true);

        refresh(patches);

        patches.addListener((ListChangeListener<PatchDTO>) c ->
                refresh(patches)
        );

        new Styles().apply(this, ContainerTheme.CONTAINER);

        getChildren().addAll(
                new PatchHeader(),
                scrollPane
        );
    }

    private void refresh(ObservableList<PatchDTO> patches) {
        list.getChildren().clear();

        if (patches.isEmpty()) {
            EmptyStateView emptyView = new EmptyStateView(
                    "Patch",
                    "It seems you don't have any patches yet.",
                    () -> {
                        GlobalModel.setSwitchProjectsModal(true);
                        GlobalModel.setSwitchCreatePatchProjectModal(true);
                    }
            );
            VBox.setVgrow(emptyView, Priority.ALWAYS);
            list.getChildren().add(emptyView);
            return;
        }

        for (int i = 0; i < patches.size(); i++) {
            list.getChildren().add(new PatchContainer(patches.get(i), i % 2 == 0 ? true : false));
        }
    }
}
