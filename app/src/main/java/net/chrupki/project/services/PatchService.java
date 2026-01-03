package net.chrupki.project.services;

import net.chrupki.database.dao.PatchDAO;

public class PatchService {
    public void createPatch(
            String projectName,
            int versionId,
            String type,
            String content
    ) {
        PatchDAO.insert(
                projectName,
                versionId,
                type,
                content
        );
    }
}
