package net.chrupki.database.dao;

import net.chrupki.model.HubModel;
import net.chrupki.database.DatabaseHub;
import net.chrupki.dto.PatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatchDAO {
    public static int insert(int versionId, int tagId, String content) {
        String sql = """
        INSERT INTO notes (version_id, tag_id, content)
        VALUES (?, ?, ?)
    """;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS
             )) {

            stmt.setInt(1, versionId);
            stmt.setInt(2, tagId);
            stmt.setString(3, content);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // ← id auto-généré
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }


    public static String findPatch(int id, int vid) {
        String sql = "SELECT patch FROM notes WHERE id = ? AND version_id = ?";

        String projectName = HubModel.projectModel().getName().get();
        if (projectName == null || projectName.isBlank()) return null;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, vid);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("patch");
                }
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String findContent(int id, int vid) {
        String sql = "SELECT content FROM notes WHERE id = ? AND version_id = ?";

        String projectName = HubModel.projectModel().getName().get();
        if (projectName == null || projectName.isBlank()) return null;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, vid);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("content");
                }
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> findByType(String projectName, String type) {

        List<String> result = new ArrayList<>();

        String sql = "SELECT content FROM notes WHERE patch = ?";

        if (projectName == null || projectName.isBlank()) return List.of();

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getString("content"));
                }
                return result;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public static boolean deleteThis(Integer id, Integer vid) {

        String sql = "DELETE FROM notes WHERE id = ? AND version_id = ?";

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, vid);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean deleteAll(Integer vid) {

        String sql = "DELETE FROM notes WHERE version_id = ?";

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vid);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean renameContent(Integer id, Integer vid, String content) {
        String sql = """
            UPDATE notes
            SET content = ?
            WHERE id = ? AND version_id = ?
            """;

        String projectName = HubModel.projectModel().getName().get();

        if (projectName == null || projectName.isBlank()) return false;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, content);
            stmt.setInt(2, id);
            stmt.setInt(3, vid);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean renamePatch(Integer id, Integer vid, Integer tid) {
        String sql = """
            UPDATE notes
            SET tag_id = ?
            WHERE id = ? AND version_id = ?
            """;

        String projectName = HubModel.projectModel().getName().get();

        if (projectName == null || projectName.isBlank()) return false;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tid);
            stmt.setInt(2, id);
            stmt.setInt(3, vid);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean doesThisPatchByIdsExist(Integer id, Integer vid) {
        String sql = """
                SELECT id
                FROM notes
                WHERE id = ? AND version_id = ?
                ORDER BY id ASC
                """;

        String projectName = HubModel.projectModel().getName().get();

        if (projectName == null || projectName.isBlank()) return false;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, vid);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public static List<PatchDTO> findAll(String projectName) throws Exception {
        List<PatchDTO> result = new ArrayList<>();
        String sql = """
            SELECT *
            FROM notes
            WHERE version_id = ?
            ORDER BY id ASC
            """;

        if (projectName == null || projectName.isBlank()) return List.of();

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, HubModel.versionModel().getId().get());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new PatchDTO(rs.getString("content"),
                            rs.getInt("tag_id"),
                            rs.getInt("id"),
                            rs.getInt("version_id")));
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
