package net.chrupki.ui.view.pages.project.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.view.pages.project.dto.VersionDTO;

public class Version extends VBox {

    private final VBox list = new VBox();

    public Version() {

        ObservableList<VersionDTO> versions = ProjectModel.getVersions();

        VBox.setVgrow(list, Priority.ALWAYS);

        refresh(versions);

        versions.addListener((ListChangeListener<VersionDTO>) c ->
                refresh(versions)
        );

        getStyleClass().add("project-panel");
        getChildren().addAll(
                new VersionHeader(),
                list);
    }

    private void refresh(ObservableList<VersionDTO> versions) {
        list.getChildren().clear();

        for (VersionDTO v : versions) {
            list.getChildren().add(new VersionContainer(v, HubController.getVersionController()::selectVersion));
        }
    }

}
