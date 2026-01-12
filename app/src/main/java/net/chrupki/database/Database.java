package net.chrupki.database;


import net.chrupki.app.AppPath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class Database {
    public static Connection getConnection(String name) throws Exception {

        Path projectDir = AppPath.getDataDir()
                .resolve("projects")
                .resolve(name);

        Files.createDirectories(projectDir);

        Path dbPath = projectDir.resolve("project.db");
        String url = "jdbc:sqlite:" + dbPath.toAbsolutePath();

        return DriverManager.getConnection(url);
    }
}
