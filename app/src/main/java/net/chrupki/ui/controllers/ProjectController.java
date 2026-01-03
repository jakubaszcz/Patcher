package net.chrupki.ui.controllers;

import net.chrupki.app.AppData;
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
        AppData.setCurrentProjectName(projectName);
        AppData.setVersionSelected(false);
    }
}
