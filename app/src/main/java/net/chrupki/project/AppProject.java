package net.chrupki.project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.model.HubModel;
import net.chrupki.app.AppPath;
import net.chrupki.database.DatabaseHub;
import net.chrupki.database.DatabaseInitializer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class AppProject {

    private static List<Path> projects = new ArrayList<>();


    private AppProject() {
    }

    public static void FetchProject() throws IOException {
        Path path = AppPath.getDataDir().resolve("projects");
        Files.createDirectories(path);
        try (var stream = Files.list(path)) {
            stream
                    .filter(Files::isDirectory)
                    .forEach(projects::add);
        }
    }

    public static void CreateProject(ProjectDTO projectDTO) {
        Path path = AppPath.getDataDir();
        Path directory = path.resolve("projects");
        Path projectPath = directory.resolve(projectDTO.getName());

        if (!projectPath.isAbsolute()) return;

        try {
            Files.createDirectories(projectPath);
            AddProjects(projectPath);
            DatabaseInitializer.init(projectDTO.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void AddProjects(Path path) {
        for (Path p : projects) {
            if (p.equals(path)) return;
        }
        projects.add(path);
    }

    public static List<ProjectDTO> FetchAllProjectNames() throws IOException {
        Path path = AppPath.getDataDir().resolve("projects");
        List<ProjectDTO> projectsName = new ArrayList<>();

        if (!Files.exists(path)) {
            return projectsName;
        }

        try (var stream = Files.list(path)) {
            stream
                    .filter(Files::isDirectory)
                    .map(p -> new ProjectDTO(p.getFileName().toString()))
                    .forEach(projectsName::add);
        }

        return projectsName;
    }

    public static List<String> FetchAllTemplates() throws IOException {
        Path path = AppPath.getDataDir().resolve("templates");
        List<String> templates = new ArrayList<>();

        if (!Files.exists(path)) {
            return templates;
        }

        try (var stream = Files.list(path)) {
            stream
                    .filter(p -> Files.isRegularFile(p) && p.getFileName().toString().endsWith(".md"))
                    .map(p -> p.getFileName().toString())
                    .forEach(templates::add);
        }

        return templates;
    }

    public static String fetchTemplateContent(String template) throws IOException {

        Path path = AppPath.getDataDir()
                .resolve("templates")
                .resolve(template);

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IOException("Template not found or not a file: " + path);
        }

        return Files.readString(path);
    }

    public static void renameProject(String oldName, String newName) throws IOException {
        DatabaseHub.getInstance().closeAll();

        Path oldPath = AppPath.getDataDir().resolve("projects").resolve(oldName);
        Path newPath = AppPath.getDataDir().resolve("projects").resolve(newName);

        try { Thread.sleep(100); } catch (InterruptedException e) { }

        Files.move(oldPath, newPath);
    }

    public static void deleteProject() throws IOException {
        Path projectDir = AppPath.getDataDir()
                .resolve("projects")
                .resolve(HubModel.projectModel().getName().get());

        DatabaseHub.getInstance().closeAll();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

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
    }

    public static ObservableList<StringProperty> FetchAllProjectPropertiesName() throws IOException {
        Path path = AppPath.getDataDir().resolve("projects");
        ObservableList<StringProperty> projectsName = FXCollections.observableArrayList();;

        if (!Files.exists(path)) {
            return projectsName;
        }

        try (var stream = Files.list(path)) {
            stream
                    .filter(Files::isDirectory)
                    .map(p -> p.getFileName().toString())
                    .forEach(name -> projectsName.add(new SimpleStringProperty(name)));
        }

        return projectsName;
    }

    public static List<Path> GetProjects() {
        return projects;
    }
}
