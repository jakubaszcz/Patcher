package net.chrupki.ui.controllers;

import net.chrupki.app.AppContext;
import net.chrupki.app.AppData;
import net.chrupki.request.PatchRequest;
import net.chrupki.project.services.PatchService;
import net.chrupki.ui.model.ProjectModel;

public class PatchController {

    private final PatchService service;
    private final ProjectModel model;

    public PatchController(PatchService service, ProjectModel model) {
        this.service = service;
        this.model = model;
    }

    public void createPatch(PatchRequest request) {
        String projectName = AppContext.projectContext().getName().get();
        if (projectName == null || projectName.isBlank()) { throw new IllegalStateException("No project selected");}

        Integer versionId = AppData.getCurrentVersionId();
        if (versionId == null) { throw new IllegalStateException("No version selected");}

        service.createPatch(
                projectName,
                versionId,
                request.type(),
                request.name()
        );
    }

    public void loadPatches() throws Exception {
        String projectName = AppData.getCurrentProjectName();
        model.getPatches().setAll(
                service.fetchVersions(projectName)
        );
    }
}
