package net.chrupki.ui.controllers;

import javafx.beans.property.StringProperty;
import net.chrupki.app.AppContext;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.model.Version;
import net.chrupki.project.AppProject;
import net.chrupki.project.services.VersionService;
import net.chrupki.ui.model.ProjectModel;

import java.io.IOException;
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
        StringProperty currentProject = AppContext.projectContext().getName();

        currentProject.addListener((obs, oldProject, newProject) -> {
            try {
                loadVersions(newProject);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void createVersion(String versionName) {
        String projectName = AppContext.projectContext().getName().get();

        try {
            Version version = service.createVersion(projectName, versionName);
            model.getVersions().add(version);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectVersion(Integer index) {
        AppContext.versionContext().setId(index);

        model.getPatches().clear();

        try {
            model.getPatches().addAll(Objects.requireNonNull(PatchDAO.findAll(AppContext.projectContext().getName().get())));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void editVersion(Integer index) {
        model.setEditVersionProperty(true);
    }

    public void loadVersions(String projectName) throws Exception {
        model.getVersions().setAll(
                service.fetchVersions(projectName)
        );
    }

    public void saveVersion(Integer id, String version) {
        closeModal();
    }

    public void closeModal() {
        model.setEditActiveProperty(false);
        model.setEditVersionProperty(false);
    }
}
