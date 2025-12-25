package net.chrupki.project;

import net.chrupki.utils.AppPaths;
import net.chrupki.database.Database;
import net.chrupki.database.DatabaseConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AppProject {

    private static List<Path> projects = new ArrayList<>();


    private AppProject() {
    }

    public static void FetchProject() throws IOException {
        Path path = AppPaths.GetDataDir().resolve("projects");

        try (var stream = Files.list(path)) {
            stream
                    .filter(Files::isDirectory)
                    .forEach(projects::add);
        }
    }

    public static void CreateProject(String name) {
        Path path = AppPaths.GetDataDir();
        Path directory = path.resolve("projects");
        Path projectPath = directory.resolve(name);

        if (!projectPath.isAbsolute()) return;

        try {
            Files.createDirectories(projectPath);
            AddProjects(projectPath);
            Database.CreateProjectDatabase(name);
            DatabaseConnection.ConnectProjectDB(name);
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

        for (var p : projects) {
            System.out.println(p.toString());
        }
    }

    public static List<String> FetchAllProjectNames() throws IOException {
        Path path = AppPaths.GetDataDir().resolve("projects");
        List<String> projectsName = new ArrayList<>();

        if (!Files.exists(path)) {
            return projectsName;
        }

        try (var stream = Files.list(path)) {
            stream
                    .filter(Files::isDirectory)
                    .map(p -> p.getFileName().toString())
                    .forEach(projectsName::add);
        }

        return projectsName;
    }

    public static List<Path> GetProjects() {
        return projects;
    }
}
