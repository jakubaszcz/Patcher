package net.chrupki.project.services;

import net.chrupki.database.dao.PatchDAO;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.Patch;
import net.chrupki.model.Version;

import java.util.List;

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

    public List<Patch> fetchVersions(String projectName) throws Exception {
        if (projectName == null || projectName.isBlank()) {
            return List.of();
        }

        return PatchDAO.findAll(projectName);
    }
}
