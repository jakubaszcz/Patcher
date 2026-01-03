package net.chrupki.ui.components;

import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.Version;
import net.chrupki.ui.components.container.VersionContainer;
import net.chrupki.ui.model.ProjectModel;

public class VersionListComponent extends VBox {


    private ProjectModel model;

    public VersionListComponent(ProjectModel model) throws Exception {
        this.model = model;

        StringProperty currentProject = AppData.getPropertyCurrentProjectName();

        VBox view = new VBox(10);
        this.getChildren().add(view);

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
                        view.getChildren().add(new VersionContainer(v.getVersion(), v.getId()));
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

        versions.setAll(
                VersionDAO.findAll(AppData.getCurrentProjectName())
        );
    }
}
