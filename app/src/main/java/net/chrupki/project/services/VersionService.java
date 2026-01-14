package net.chrupki.project.services;

import net.chrupki.database.dao.PatchDAO;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.Version;

import java.util.List;

public class VersionService {
    public Version createVersion(String project, String version) {
        if (project == null || project.isBlank()) {
            throw new IllegalArgumentException("Project name is required");
        }
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("Version name is required");
        }

        int id = VersionDAO.insert(version);
        return new Version(id, version);
    }

    public List<Version> fetchVersions(String projectName) throws Exception {
        if (projectName == null || projectName.isBlank()) {
            return List.of();
        }

        return VersionDAO.findAll();
    }

    public void deleteVersion(Integer id) {
        if (id == null || id < 0 || !VersionDAO.doesThisVersionByIdExist(id)) {
            throw new IllegalArgumentException("Version id is unavailable or the id is corrupted !");
        }

        PatchDAO.deleteAll(id);
        VersionDAO.deleteThis(id);
    }

    public void renameVersion(Integer id, String version) {

        if (id == null || id < 0 || !VersionDAO.doesThisVersionByIdExist(id)) {
            throw new IllegalArgumentException("Version id is unavailable or the id is corrupted !");
        }

        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("Version is blank or don't exist !");
        }

        if (!VersionDAO.renameVersion(id, version)) {
            throw new IllegalArgumentException("Version couldn't be renamed !");
        }
    }
}