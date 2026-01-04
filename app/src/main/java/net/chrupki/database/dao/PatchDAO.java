package net.chrupki.database.dao;

import net.chrupki.app.AppData;
import net.chrupki.database.Database;
import net.chrupki.model.Patch;
import net.chrupki.model.Version;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatchDAO {
    public static void insert(String projectName, int id, String type, String content) {
        String sql = "INSERT INTO notes (version_id, patch, content) VALUES (?, ?, ?)";

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

    public static List<Patch> findAll(String projectName) throws Exception {
        List<Patch> result = new ArrayList<>();
        String sql = """
            SELECT *
            FROM notes
            WHERE version_id = ?
            ORDER BY id ASC
            """;

        if (projectName == null || projectName.isBlank()) return List.of();

        try (Connection conn = Database.getConnection(projectName);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, AppData.getCurrentVersionId());

            ResultSet rs1 = stmt.executeQuery();
            rs1.next();
            System.out.println(rs1.getString("content"));
            System.out.println(rs1.getString("patch"));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new Patch(
                            rs.getString("content"),
                            rs.getString("patch"))
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

}
