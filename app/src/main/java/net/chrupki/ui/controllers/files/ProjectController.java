package net.chrupki.ui.controllers.files;

import net.chrupki.model.HubModel;
import net.chrupki.project.AppProject;
import net.chrupki.project.services.HubService;
import net.chrupki.ui.model.GlobalModel;

import java.io.IOException;

public class ProjectController {

    public void createProject(String name) {
        AppProject.CreateProject(name);
        HubModel.projectModel().setName(name);
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

    public void closeCreateProjectsModal() {
        GlobalModel.setSwitchProjectsModal(false);
        GlobalModel.setSwitchCreateProjectsModal(false);
    }

    public void openEditProjectsModal() {
        GlobalModel.setSwitchProjectsModal(true);
        GlobalModel.setSwitchEditProjectsModal(true);
    }

    public void closeEditProjectsModal() {
        GlobalModel.setSwitchProjectsModal(false);
        GlobalModel.setSwitchEditProjectsModal(false);
    }

    public void selectProject(String projectName) {
        HubModel.projectModel().setName(projectName);
    }

    public void saveProject(String oldProjectName, String newProjectName) {

        if (oldProjectName == null || oldProjectName.isBlank()) {
            throw new IllegalStateException("No project selected");
        }

        if (newProjectName == null || newProjectName.isBlank()) {
            throw new IllegalArgumentException("New project name is empty");
        }

        try {
            AppProject.renameProject(oldProjectName, newProjectName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to rename project", e);
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

        HubModel.projectModel().setName(null);
        loadProjects();
    }


    public void closeModal() {
        GlobalModel.setEditActiveProperty(false);
        GlobalModel.setEditProjectProperty(false);
    }
}
