package net.chrupki.database;

import net.chrupki.utils.AppData;

import java.sql.*;

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

    public static void CreateVersion() {
        String sql = "INSERT INTO version (version) VALUES (?)";

        try (Connection conn = Database.CreateProjectDatabase(AppData.GetCurrentProjectName());
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "x.y.z");

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
