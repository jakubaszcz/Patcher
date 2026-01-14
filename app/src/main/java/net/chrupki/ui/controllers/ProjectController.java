package net.chrupki.ui.controllers;

import net.chrupki.app.AppContext;
import net.chrupki.app.AppPath;
import net.chrupki.database.DatabaseHub;
import net.chrupki.project.AppProject;
import net.chrupki.project.services.ProjectService;
import net.chrupki.ui.model.ProjectModel;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class ProjectController {
    private final ProjectService service;
    private final ProjectModel model;

    public ProjectController(ProjectService service, ProjectModel model) {
        this.service = service;
        this.model = model;
    }

    public void createProject(String name) {
        AppProject.CreateProject(name);
        AppContext.projectContext().setName(name);
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

    public void onDelete() {
        Path projectDir = AppPath.getDataDir()
                .resolve("projects")
                .resolve(AppContext.projectContext().getName().get());

        // 1. Ferme DB
        DatabaseHub.getInstance().closeAll();

        // 2. PAUSE CRUCIALE (Windows libère les handles)
        try {
            Thread.sleep(300);  // 300ms suffisant
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        // 3. Supprime
        try {
            Files.walkFileTree(projectDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                        throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            // UI-friendly, pas RuntimeException
            return;
        }

        // 4. UI + context
        AppContext.projectContext().setName(null);
        loadProjects();
    }


    public void closeModal() {
        model.setEditActiveProperty(false);
        model.setEditProjectProperty(false);
    }
}
