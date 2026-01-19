package net.chrupki.ui.components;

import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.ui.views.pages.project.dto.VersionDTO;
import net.chrupki.ui.controllers.files.VersionController;
import net.chrupki.ui.model.ProjectModel;

public class VersionListView extends VBox {

    private final VBox view = new VBox();

    private ProjectModel model;

    public VersionListView(VersionController versionController, ProjectModel model) {
        this.model = model;

        StringProperty currentProject = AppContext.projectContext().getName();


        view.visibleProperty().bind(
                currentProject.isNotNull().and(currentProject.isNotEmpty())
        );

        view.managedProperty().bind(view.visibleProperty());

        ObservableList<VersionDTO> versions = model.getVersions();

        versions.addListener((ListChangeListener<VersionDTO>) change -> {
            while (change.next()) {

                if (change.wasRemoved()) {
                    view.getChildren().clear();
                }

                if (change.wasAdded()) {
                    for (VersionDTO v : change.getAddedSubList()) {
                        view.getChildren().add(new VersionContainer(model, v.getVersion(), v.getId(), versionController::selectVersion, versionController::editVersion));
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
                        VersionDAO.findAll()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        this.getChildren().add(view);
    }
}
