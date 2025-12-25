package net.chrupki.database;


import net.chrupki.utils.AppPaths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
    public static Connection CreateProjectDatabase(String name) throws Exception {

        Path projectDir = AppPaths.GetDataDir()
                .resolve("projects")
                .resolve(name);

        Files.createDirectories(projectDir);

        Path dbPath = projectDir.resolve("project.db");
        String url = "jdbc:sqlite:" + dbPath.toAbsolutePath();

        return DriverManager.getConnection(url);
    }
}
