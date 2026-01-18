package net.chrupki.ui.controllers.files;

import net.chrupki.app.AppContext;
import net.chrupki.project.AppProject;
import net.chrupki.project.services.HubService;
import net.chrupki.project.services.files.ProjectService;
import net.chrupki.ui.model.ProjectModel;

import java.io.IOException;

public class ProjectController {

    public void createProject(String name) {
        AppProject.CreateProject(name);
        AppContext.projectContext().setName(name);
        loadProjects();
    }

    public void loadProjects() {
        try {
            ProjectModel.getProjects().setAll(
                    HubService.getProjectService().fetchAllProjectNames()
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void openCreateProjectsModal() {
        ProjectModel.setSwitchProjectsModal(true);
        ProjectModel.setSwitchCreateProjectsModal(true);
    }

    public void selectProject(String projectName) {
        AppContext.projectContext().setName(projectName);
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
        ProjectModel.setEditProjectProperty(true);
    }

    public void onDelete() {
        try {
            AppProject.deleteProject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AppContext.projectContext().setName(null);
        loadProjects();
        closeModal();
    }


    public void closeModal() {
        ProjectModel.setEditActiveProperty(false);
        ProjectModel.setEditProjectProperty(false);
    }
}
