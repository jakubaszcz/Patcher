package net.chrupki.ui.controllers.files;

import javafx.beans.property.StringProperty;
import net.chrupki.app.AppContext;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.model.Version;
import net.chrupki.project.services.HubService;
import net.chrupki.project.services.files.VersionService;
import net.chrupki.ui.controllers.files.dtos.EditVersion;
import net.chrupki.ui.model.ProjectModel;

import java.util.Objects;

public class VersionController {
    public VersionController() {
        observeCurrentProject();
    }

    private void observeCurrentProject() {
        StringProperty currentProject = AppContext.projectContext().getName();

        currentProject.addListener((obs, oldProject, newProject) -> {
            try {
                loadVersions();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void createVersion(String versionName) {
        String projectName = AppContext.projectContext().getName().get();

        try {
            Version version = HubService.getVersionService().createVersion(projectName, versionName);
            ProjectModel.getVersions().add(version);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectVersion(Integer index) {
        AppContext.versionContext().setId(index);

        ProjectModel.getPatches().clear();

        try {
            ProjectModel.getPatches().addAll(Objects.requireNonNull(PatchDAO.findAll(AppContext.projectContext().getName().get())));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void editVersion(Integer index) {
        ProjectModel.setEditVersionProperty(true);
    }

    public void loadVersions() {
        try {
            ProjectModel.getVersions().setAll(
                    HubService.getVersionService().fetchVersions(AppContext.projectContext().getName().get())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveVersion(EditVersion version) {


        HubService.getVersionService().renameVersion(version.getId(), version.getNewName());
        loadVersions();
        closeModal();
    }

    public void deleteVersion(Integer id) {
        HubService.getVersionService().deleteVersion(id);

        loadVersions();
        closeModal();
    }

    public void closeModal() {
        ProjectModel.setEditActiveProperty(false);
        ProjectModel.setEditVersionProperty(false);
    }
}
