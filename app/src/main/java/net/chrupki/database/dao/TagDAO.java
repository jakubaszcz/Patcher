package net.chrupki.database.dao;

import net.chrupki.database.DatabaseHub;
import net.chrupki.dto.TagDTO;
import net.chrupki.dto.VersionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {
    public static TagDTO insert(TagDTO tagDTO) {
        String sql = "INSERT INTO tags (tag) VALUES (?)";

        try (Connection conn = DatabaseHub.getInstance().getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tagDTO.getName());
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
}
