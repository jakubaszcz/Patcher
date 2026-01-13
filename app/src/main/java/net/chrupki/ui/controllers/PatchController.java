package net.chrupki.ui.controllers;

import net.chrupki.app.AppContext;
import net.chrupki.model.Patch;
import net.chrupki.request.PatchRequest;
import net.chrupki.project.services.PatchService;
import net.chrupki.ui.controllers.dtos.EditPatch;
import net.chrupki.ui.controllers.dtos.EditVersion;
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

        Integer versionId = AppContext.versionContext().getId().get();
        if (versionId == null) { throw new IllegalStateException("No version selected");}

        model.getPatches().add(new Patch(
                        request.name(),
                        request.type(),
                        service.createPatch(
                                projectName,
                                versionId,
                                request.type(),
                                request.name()
                        ),
                        request.vid()
                )
        );
    }

    public void loadPatches() {
        String projectName = AppContext.projectContext().getName().get();
        try {
            model.getPatches().setAll(
                    service.fetchVersions(projectName)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void savePatch(EditPatch patch) {
        service.savePatch(patch.getId(), patch.getVid(), patch.getContent(), patch.getType());
        loadPatches();
        closeModal();
    }


    public void closeModal() {
        model.setEditActiveProperty(false);
        model.setEditPatchProperty(false);
    }
}
