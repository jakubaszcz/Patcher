package net.chrupki.database;

import net.chrupki.database.schema.Tables;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void init(String name) throws Exception {
        try (
                Connection conn = Database.getConnection(name);
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(Tables.VERSION);
            stmt.execute(Tables.PATCH);
            stmt.execute(Tables.TAG);
        }
    }
}
