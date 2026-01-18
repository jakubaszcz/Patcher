package net.chrupki.project.services.files;

import net.chrupki.database.dao.PatchDAO;
import net.chrupki.model.Patch;

import java.util.List;

public class PatchService {
    public int createPatch(
            String projectName,
            int versionId,
            String type,
            String content
    ) {
        return PatchDAO.insert(
                versionId,
                type,
                content
        );
    }

    public List<Patch> fetchVersions(String projectName) throws Exception {
        if (projectName == null || projectName.isBlank()) {
            return List.of();
        }

        return PatchDAO.findAll(projectName);
    }

    public void deletePatch(Integer id, Integer vid) {
        if (id == null || vid == null || !PatchDAO.doesThisPatchByIdsExist(id, vid)) {
            throw new IllegalArgumentException("Patch ids are unavailable or corrupted !");
        }

        PatchDAO.deleteThis(id, vid);
    }

    public void savePatch(Integer id, Integer vid, String content, String type) {
        if (id == null || vid == null || !PatchDAO.doesThisPatchByIdsExist(id, vid)) {
            throw new IllegalArgumentException("Patch ids are unavailable or corrupted !");
        }

        String currentContent = PatchDAO.findContent(id, vid);
        if (content != null && !content.isEmpty() && !content.equals(currentContent)) {
            PatchDAO.renameContent(id, vid, content);
        }

        String currentType = PatchDAO.findPatch(id, vid);
        if (type != null && !type.isEmpty() && !type.equals(currentType)) {
            PatchDAO.renamePatch(id, vid, type);
        }
    }

}
