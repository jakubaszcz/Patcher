package net.chrupki.ui.views.pages.project.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.pages.project.dto.VersionDTO;
import net.chrupki.ui.views.pages.projects.EmptyProjectsView;
import net.chrupki.ui.views.pages.projects.components.ProjectsContainer;
import net.chrupki.ui.views.pages.projects.dto.ProjectContainerDTO;

public class Version extends VBox {

    private final VBox list = new VBox();

    public Version() {

        ObservableList<VersionDTO> versions = ProjectModel.getVersions();

        VBox.setVgrow(list, Priority.ALWAYS);

        refresh(versions);

        versions.addListener((ListChangeListener<VersionDTO>) c ->
                refresh(versions)
        );

        getStyleClass().add("version-panel");
        getChildren().addAll(
                new VersionHeader(),
                list);
    }

    private void refresh(ObservableList<VersionDTO> versions) {
        list.getChildren().clear();

        for (VersionDTO v : versions) {
            list.getChildren().add(new VersionContainer(v));
        }
    }

}
