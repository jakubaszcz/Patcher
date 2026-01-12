package net.chrupki.project.services;

import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.Version;

import java.util.List;

public class VersionService {
    public Version createVersion(String project, String version) {
        if (project == null || project.isBlank()) { throw new IllegalArgumentException("Project name is required");}
        if (version == null || version.isBlank()) { throw new IllegalArgumentException("Version name is required");}

        System.out.println("Creating version " + version + " for project " + project);

        int id = VersionDAO.insert(project, version);
        return new Version(id, version);
    }

    public List<Version> fetchVersions(String projectName) throws Exception {
        if (projectName == null || projectName.isBlank()) {
            return List.of();
        }

        return VersionDAO.findAll(projectName);
    }
}
