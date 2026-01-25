package net.chrupki.database.dao;

import net.chrupki.database.DatabaseHub;
import net.chrupki.database.dao.dto.VersionDatabaseDTO;
import net.chrupki.ui.view.pages.project.dto.VersionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VersionDAO {

    public static int insert(VersionDatabaseDTO version) {
        String sql = "INSERT INTO versions (version, type) VALUES (?, ?)";

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, version.getVersion());
            stmt.setString(2, version.getType());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static String findNameById(int id) throws Exception {
        String sql = "SELECT version FROM versions WHERE id = ?";

        try (Connection conn = DatabaseHub.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                return rs.getString("version");
            }
    }

    public static boolean doesThisVersionByIdExist(Integer id) {
        String sql = """
                SELECT id
                FROM versions
                WHERE id = ?
                ORDER BY id ASC
                """;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

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

    public static boolean renameVersion(Integer id, String name) {
        String sql = """
            UPDATE versions
            SET version = ?
            WHERE id = ?
            """;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteThis(Integer id) {

        String sql = "DELETE FROM versions WHERE id = ?";

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


    public static List<VersionDTO> findAll() {
        List<VersionDTO> result = new ArrayList<>();
        String sql = """
                SELECT id, version, type
                FROM versions
                WHERE id
                ORDER BY id ASC
                """;

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new VersionDTO(
                            rs.getInt("id"),
                            rs.getString("version"),
                            rs.getString("type")
                    ));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
