package net.chrupki.database.dao;

import net.chrupki.database.DatabaseHub;
import net.chrupki.dto.TagDTO;
import net.chrupki.dto.VersionDTO;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.files.dtos.EditTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {
    public static TagDTO insert(String name) {
        String sql = "INSERT INTO tags (tag) VALUES (?)";

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return new TagDTO(
                            rs.getString("tag"),
                            rs.getInt("id")
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<TagDTO> all() {
        List<TagDTO> result = new ArrayList<>();

        String sql = """
        SELECT *
        FROM tags
        ORDER BY id ASC
        """;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.add(new TagDTO(
                        rs.getString("tag"),
                        rs.getInt("id")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static boolean save(EditTag editTag) {
        String sql = """
            UPDATE tags
            SET tag = ?
            WHERE id = ?
            """;

        String projectName = HubModel.projectModel().getName().get();

        if (projectName == null || projectName.isBlank()) return false;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, editTag.getName());
            stmt.setInt(2, editTag.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(Integer id) {

        String sql = "DELETE FROM tags WHERE id = ?";

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
