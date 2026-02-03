package net.chrupki.project.services.files;

import net.chrupki.database.dao.PatchDAO;
import net.chrupki.dto.PatchDTO;

import java.util.List;

public class PatchService {
    public int createPatch(
            String projectName,
            int versionId,
            int tagId,
            String content
    ) {

        try {
            return PatchDAO.insert(
                    versionId,
                    tagId,
                    content
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public List<PatchDTO> fetchVersions(String projectName) throws Exception {
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

    public void savePatch(Integer id, Integer vid, String content, Integer tid) {
        if (id == null || vid == null || !PatchDAO.doesThisPatchByIdsExist(id, vid)) {
            throw new IllegalArgumentException("Patch ids are unavailable or corrupted !");
        }

        String currentContent = PatchDAO.findContent(id, vid);
        if (content != null && !content.isEmpty() && !content.equals(currentContent)) {
            PatchDAO.renameContent(id, vid, content);
        }

        String currentType = PatchDAO.findPatch(id, vid);
        if (tid != null) {
            PatchDAO.renamePatch(id, vid, tid);
        }
    }

}
