package net.chrupki.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void InitDB() {
        String createReleaseTable = """
            CREATE TABLE IF NOT EXISTS release (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                version TEXT NOT NULL,
                date TEXT NOT NULL
            );
        """;

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createReleaseTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
