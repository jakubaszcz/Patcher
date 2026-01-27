package net.chrupki.ui.controllers;

import net.chrupki.ui.controllers.files.*;

public class HubController {
    private static final ProjectController projectController = new ProjectController();
    private static final VersionController versionController = new VersionController();
    private static final PatchController patchController = new PatchController();
    private static final TemplateController templateController = new TemplateController();

    // Export
    private static final ExportController exportController = new ExportController();

    // Getters
    public static ProjectController getProjectController() { return projectController; }
    public static VersionController getVersionController() { return versionController; }
    public static PatchController getPatchController() { return patchController; }
    public static ExportController getExportController() { return exportController; }
    public static TemplateController getTemplateController() { return templateController; }
}
