package net.chrupki.ui.controllers.files;

import javafx.beans.property.StringProperty;
import net.chrupki.model.HubModel;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.dto.VersionDTO;
import net.chrupki.project.services.HubService;
import net.chrupki.ui.controllers.files.dtos.EditVersion;
import net.chrupki.ui.model.GlobalModel;

import java.util.Objects;

public class VersionController {
    public VersionController() {
        observeCurrentProject();
    }

    private void observeCurrentProject() {
        StringProperty currentProject = HubModel.projectModel().getName();

        currentProject.addListener((obs, oldProject, newProject) -> {
            try {
                loadVersions();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void createVersion(String versionName, String type) {
        String projectName = HubModel.projectModel().getName().get();

        try {
            VersionDTO version = HubService.getVersionService().createVersion(projectName, versionName, type);
            GlobalModel.getVersions().add(version);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectVersion(VersionDTO versionDTO) {
        HubModel.versionModel().from(versionDTO);

        GlobalModel.getPatches().clear();

        try {
            GlobalModel.getPatches().addAll(Objects.requireNonNull(PatchDAO.findAll(HubModel.projectModel().getName().get())));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void editVersion(Integer index) {
        GlobalModel.setEditVersionProperty(true);
    }

    public void loadVersions() {
        try {
            GlobalModel.getVersions().setAll(
                    HubService.getVersionService().fetchVersions(HubModel.projectModel().getName().get())
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

        HubModel.versionModel().clear();

        loadVersions();
        closeModal();
    }

    public void closeModal() {
        GlobalModel.setSwitchProjectModal(false);
        GlobalModel.setSwitchCreateVersionProjectModal(false);
        GlobalModel.setSwitchEditVersionProjectModal(false);
    }
}
