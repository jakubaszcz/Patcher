package net.chrupki.ui.components;

import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.Version;
import net.chrupki.ui.controllers.VersionController;
import net.chrupki.ui.model.ProjectModel;

public class VersionListView extends VBox {

    private final VBox view = new VBox();

    private ProjectModel model;

    public VersionListView(VersionController versionController, ProjectModel model) throws Exception {
        this.model = model;

        StringProperty currentProject = AppData.getPropertyCurrentProjectName();


        view.visibleProperty().bind(
                currentProject.isNotNull().and(currentProject.isNotEmpty())
        );

        view.managedProperty().bind(view.visibleProperty());

        ObservableList<Version> versions = model.getVersions();

        versions.addListener((ListChangeListener<Version>) change -> {
            while (change.next()) {

                if (change.wasRemoved()) {
                    view.getChildren().clear();
                }

                if (change.wasAdded()) {
                    for (Version v : change.getAddedSubList()) {
                        view.getChildren().add(new VersionContainer(model, v.getVersion(), v.getId(), versionController::selectVersion));
                    }
                }
            }
        });

        currentProject.addListener((obs, oldProject, newProject) -> {

            if (newProject == null || newProject.isBlank()) {
                versions.clear();
                return;
            }

            try {
                versions.setAll(
                        VersionDAO.findAll(newProject)
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        this.getChildren().add(view);
    }
}
