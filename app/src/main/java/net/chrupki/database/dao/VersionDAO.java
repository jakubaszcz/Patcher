package net.chrupki.database.dao;

import net.chrupki.app.AppContext;
import net.chrupki.database.Database;
import net.chrupki.model.Version;
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

    public static int  insert(String projectName, String version) {
        String sql = "INSERT INTO versions (version) VALUES (?)";

        try (Connection conn = Database.getConnection(projectName);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, version);

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

    public static String findNameById(String projectName, int id) throws Exception {
        String sql = "SELECT version FROM versions WHERE id = ?";

        if (projectName == null || projectName.isBlank()) return null;

        try (Connection conn = Database.getConnection(projectName);
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

        String projectName = AppContext.projectContext().getName().get();

        if (projectName == null || projectName.isBlank()) return false;

        try (Connection conn = Database.getConnection(projectName);
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

        String projectName = AppContext.projectContext().getName().get();

        if (projectName == null || projectName.isBlank()) return false;

        try (Connection conn = Database.getConnection(projectName);
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


    public static List<Version> findAll(String projectName) throws Exception {
        List<Version> result = new ArrayList<>();
        String sql = """
                SELECT id, version
                FROM versions
                WHERE id
                ORDER BY id ASC
                """;

        if (projectName == null || projectName.isBlank()) return List.of();


        try (Connection conn = Database.getConnection(projectName);
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new Version(
                            rs.getInt("id"),
                            rs.getString("version")
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
