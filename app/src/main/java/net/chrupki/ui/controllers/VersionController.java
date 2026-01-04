package net.chrupki.ui.controllers;

import javafx.beans.property.StringProperty;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.model.Version;
import net.chrupki.project.services.VersionService;
import net.chrupki.ui.model.ProjectModel;

import java.util.Objects;

public class VersionController {
    private final VersionService service;
    private final ProjectModel model;

    public VersionController(VersionService service, ProjectModel model) {
        this.service = service;
        this.model = model;

        observeCurrentProject();
    }

    private void observeCurrentProject() {
        StringProperty currentProject = AppData.getPropertyCurrentProjectName();

        currentProject.addListener((obs, oldProject, newProject) -> {
            try {
                loadVersions(newProject);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void createVersion(String versionName) {
        String projectName = AppData.getPropertyCurrentProjectName().get();

        try {
            Version version = service.createVersion(projectName, versionName);
            model.getVersions().add(version);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectVersion(Integer index) {
        AppData.setCurrentVersionId(index);

        model.getPatches().clear();

        try {
            model.getPatches().addAll(Objects.requireNonNull(PatchDAO.findAll(AppData.getCurrentProjectName())));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void loadVersions(String projectName) throws Exception {
        model.getVersions().setAll(
                service.fetchVersions(projectName)
        );
    }
}
