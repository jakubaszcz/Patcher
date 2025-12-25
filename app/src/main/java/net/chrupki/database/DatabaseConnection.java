package net.chrupki.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseConnection {

    private static String createVersionTable = """
            CREATE TABLE IF NOT EXISTS version (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                version TEXT NOT NULL
            );
        """;

    private static String createPatchTable = """
            CREATE TABLE IF NOT EXISTS note (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                version_id INTEGER,
                patch TEXT NOT NULL,
                content TEXT NOT NULL
            );
        """;

    public static void ConnectProjectDB(String name) throws Exception {
        try (
                Connection conn = Database.CreateProjectDatabase(name);
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(createVersionTable);
            stmt.execute(createPatchTable);
        }
    }

}
