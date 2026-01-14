package net.chrupki.database;

import net.chrupki.app.AppContext;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHub {

    private static DatabaseHub INSTANCE = new DatabaseHub();
    private Connection currentConnection = null;

    private DatabaseHub() {}

    public static DatabaseHub getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        if (AppContext.projectContext().getName().get().isEmpty()) return null;
        if (currentConnection != null) {
            try {
                currentConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            currentConnection = Database.getConnection(AppContext.projectContext().getName().get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return currentConnection;
    }

    public void closeAll() {
        if (currentConnection != null) {
            try {
                currentConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
