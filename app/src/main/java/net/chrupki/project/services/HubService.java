package net.chrupki.project.services;

import net.chrupki.project.services.files.PatchService;
import net.chrupki.project.services.files.ProjectService;
import net.chrupki.project.services.files.VersionService;
import net.chrupki.project.services.files.exports.MarkdownExportService;

public class HubService {
    private static final ProjectService projectService = new ProjectService();
    private static final VersionService versionService = new VersionService();
    private static final PatchService patchService = new PatchService();

    // Export
    private static final MarkdownExportService markdownExportService = new MarkdownExportService();

    // Getters
    public static ProjectService getProjectService() { return projectService; }
    public static VersionService getVersionService() { return versionService; }
    public static PatchService getPatchService() { return patchService; }
    public static MarkdownExportService getMarkdownExportService() { return markdownExportService; }
}
