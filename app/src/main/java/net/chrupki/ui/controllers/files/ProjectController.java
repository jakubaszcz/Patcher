package net.chrupki.ui.controllers.files;

import net.chrupki.dto.ProjectDTO;
import net.chrupki.model.HubModel;
import net.chrupki.project.AppProject;
import net.chrupki.project.services.HubService;
import net.chrupki.ui.model.GlobalModel;

import java.io.IOException;

public class ProjectController {

    public void createProject(ProjectDTO projectDTO) {
        AppProject.CreateProject(projectDTO);
        HubModel.projectModel().from(projectDTO);
        loadProjects();
    }

    public void loadProjects() {
        try {
            GlobalModel.getProjects().setAll(
                    HubService.getProjectService().fetchAllProjectNames()
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void openCreateProjectsModal() {
        GlobalModel.setSwitchProjectsModal(true);
        GlobalModel.setSwitchCreateProjectsModal(true);
    }

    public void openEditProjectsModal() {
        GlobalModel.setSwitchProjectsModal(true);
        GlobalModel.setSwitchEditProjectsModal(true);
    }

    public void saveProject(String oldProjectName, String newProjectName, String description) {

        if (oldProjectName == null || oldProjectName.isBlank()) {
            throw new IllegalStateException("No project selected");
        }

        if (newProjectName == null || newProjectName.isBlank()) {
            throw new IllegalArgumentException("New project name is empty");
        }

        try {
            AppProject.saveProject(oldProjectName, newProjectName, description);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save project", e);
        }

        loadProjects();
        closeModal();
    }

    public void onEdit() {
        GlobalModel.setEditProjectProperty(true);
    }

    public void onDelete() {
        try {
            AppProject.deleteProject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HubModel.projectModel().clear();
        loadProjects();
    }


    public void closeModal() {
        GlobalModel.setSwitchProjectsModal(false);
        GlobalModel.setSwitchProjectModal(false);
        GlobalModel.setSwitchCreateProjectsModal(false);
        GlobalModel.setSwitchEditProjectsModal(false);
        GlobalModel.setSwitchCreateVersionProjectModal(false);
        GlobalModel.setSwitchEditVersionProjectModal(false);
        GlobalModel.setSwitchCreatePatchProjectModal(false);
        GlobalModel.setSwitchEditPatchProjectModal(false);
        GlobalModel.setSwitchExportModal(false);
        GlobalModel.setSwitchCreateTagProjectModal(false);
        GlobalModel.setSwitchEditTagProjectModal(false);
        GlobalModel.setSwitchConfirmModal(false);

        GlobalModel.setErrorMessage(null);
    }
}
