package net.chrupki.database.dao;

import net.chrupki.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatchDAO {
    public static void insert(String projectName, int id, String type, String content) {
        String sql = "INSERT INTO note (id, type, content) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection(projectName);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, type);
            stmt.setString(3, content);

            stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
