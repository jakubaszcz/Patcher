package net.chrupki.database;

import net.chrupki.project.AppProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void InitDB() throws Exception {
        String createReleaseTable = """
            CREATE TABLE IF NOT EXISTS release (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                version TEXT NOT NULL,
                date TEXT NOT NULL
            );
        """;
        for (String project : AppProject.FetchAllProjectNames()) {

            System.out.println("Project name: " + project);

            try (
                    Connection conn = Database.CreateProjectDatabase(project);
                    Statement stmt = conn.createStatement()
            ) {
                stmt.execute(createReleaseTable);
            }

        }
    }

}
