package net.chrupki.database.dao;

import net.chrupki.database.Database;
import net.chrupki.ui.model.ProjectModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VersionDAO {

    public static void insert(String projectName) {
        insert(projectName, "x.y.z");
    }

    public static void insert(String projectName, String version) {
        String sql = "INSERT INTO versions (version) VALUES (?)";

        try (Connection conn = Database.getConnection(projectName);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, version);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> findAll(String projectName) throws Exception {
        List<String> versions = new ArrayList<>();
        String sql = """
                SELECT version
                FROM versions
                WHERE id
                ORDER BY id ASC
                """;

        if (projectName == null || projectName.isBlank()) return List.of();


        try (Connection conn = Database.getConnection(projectName);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    versions.add(rs.getString("version"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return versions;
    }
}
