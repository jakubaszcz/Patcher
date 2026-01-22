package net.chrupki.ui.views.pages.project.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.pages.project.dto.PatchDTO;
import net.chrupki.ui.views.pages.project.dto.VersionDTO;

public class Patch extends VBox {

    private final VBox list = new VBox();

    public Patch() {

        ObservableList<PatchDTO> patches = ProjectModel.getPatches();

        list.visibleProperty().bind(AppContext.versionContext().getId().isNotNull());
        list.managedProperty().bind(list.visibleProperty());

        VBox.setVgrow(list, Priority.ALWAYS);

        refresh(patches);

        patches.addListener((ListChangeListener<PatchDTO>) c ->
                refresh(patches)
        );

        getStyleClass().add("project-panel");
        getChildren().addAll(
                new PatchHeader(),
                list);
    }

    private void refresh(ObservableList<PatchDTO> patches) {
        list.getChildren().clear();

        for (PatchDTO p : patches) {
            list.getChildren().add(new PatchContainer(p));
        }
    }

}
