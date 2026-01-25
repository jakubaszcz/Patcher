package net.chrupki.database.schema;

public final class Tables {

    private Tables() {}

    public static final String VERSION = """
        CREATE TABLE IF NOT EXISTS versions (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            version TEXT NOT NULL,
            type TEXT NOT NULL
        );
        """;

    public static final String PATCH = """
        CREATE TABLE IF NOT EXISTS notes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            version_id INTEGER NOT NULL,
            patch TEXT NOT NULL,
            content TEXT NOT NULL,
            FOREIGN KEY (version_id) REFERENCES versions(id)
        );
        """;
}