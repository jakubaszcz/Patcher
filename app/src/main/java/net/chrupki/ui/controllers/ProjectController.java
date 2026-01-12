package net.chrupki.ui.controllers;

import net.chrupki.app.AppContext;
import net.chrupki.project.AppProject;
import net.chrupki.project.services.ProjectService;
import net.chrupki.ui.model.ProjectModel;

import java.io.IOException;

public class ProjectController {
    private final ProjectService service;
    private final ProjectModel model;

    public ProjectController(ProjectService service, ProjectModel model) {
        this.service = service;
        this.model = model;
    }

    public void createProject(String name) {
        AppProject.CreateProject(name);
    }

    public void loadProjects() {
        try {
            model.getProjects().setAll(
                    service.fetchAllProjectNames()
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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

    public void closeModal() {
        model.setEditActiveProperty(false);
        model.setEditProjectProperty(false);
    }
}
