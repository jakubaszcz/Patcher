package net.chrupki.ui.controllers;

import net.chrupki.app.AppData;
import net.chrupki.database.Database;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.model.PatchRequest;
import net.chrupki.project.services.PatchService;

public class PatchController {

    private final PatchService service;

    public PatchController(PatchService service) {
        this.service = service;
    }

    public void createPatch(PatchRequest request) {
        String projectName = AppData.getCurrentProjectName();
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
}
