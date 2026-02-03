package net.chrupki.project.services.files;

import net.chrupki.database.dao.TagDAO;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.dto.TagDTO;
import net.chrupki.dto.VersionDTO;

import java.util.List;

public class TagService {

    public TagDTO create(String name) {
        try {
            return TagDAO.insert(name);
        } catch (Exception e) {
            return null;
        }
    }

    public List<TagDTO> fetch(String projectName) throws Exception {

        if (projectName == null || projectName.isBlank()) {
            return List.of();
        }

        return TagDAO.all();
    }

}
