package net.chrupki.project;

import net.chrupki.AppPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppProject {

    private static Path path = AppPaths.GetDataDir();
    private static Path directory = path.resolve("projects");

    private AppProject() {
    }


    public static void CreateProject(String name) {
        Path projectPath = directory.resolve(name);

        if (!projectPath.isAbsolute()) return;

        try {
            Files.createDirectories(projectPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
