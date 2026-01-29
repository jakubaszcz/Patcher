package net.chrupki.project.services;

import net.chrupki.dto.TagDTO;
import net.chrupki.project.services.files.*;
import net.chrupki.project.services.files.exports.MarkdownExportService;

public class HubService {
    private static final ProjectService projectService = new ProjectService();
    private static final VersionService versionService = new VersionService();
    private static final PatchService patchService = new PatchService();
    private static final TemplateService templateService = new TemplateService();
    private static final TagService tagService = new TagService();

    // Export
    private static final MarkdownExportService markdownExportService = new MarkdownExportService();

    // Getters
    public static ProjectService getProjectService() { return projectService; }
    public static VersionService getVersionService() { return versionService; }
    public static PatchService getPatchService() { return patchService; }
    public static MarkdownExportService getMarkdownExportService() { return markdownExportService; }
    public static TemplateService getTemplateService() { return templateService; }
    public static TagService getTagService() { return tagService; }
}
