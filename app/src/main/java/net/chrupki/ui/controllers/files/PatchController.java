package net.chrupki.ui.controllers.files;

import net.chrupki.app.AppContext;
import net.chrupki.ui.view.pages.project.dto.PatchDTO;
import net.chrupki.project.services.HubService;
import net.chrupki.request.PatchRequest;
import net.chrupki.ui.controllers.files.dtos.EditPatch;
import net.chrupki.ui.model.ProjectModel;

public class PatchController {

    public void createPatch(PatchRequest request) {
        String projectName = AppContext.projectContext().getName().get();
        if (projectName == null || projectName.isBlank()) { throw new IllegalStateException("No project selected");}

        Integer versionId = AppContext.versionContext().getId().get();
        if (versionId == null) { throw new IllegalStateException("No version selected");}

        ProjectModel.getPatches().add(new PatchDTO(
                        request.name(),
                        request.type(),
                        HubService.getPatchService().createPatch(
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
            ProjectModel.getPatches().setAll(
                    HubService.getPatchService().fetchVersions(projectName)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void savePatch(EditPatch patch) {
        HubService.getPatchService().savePatch(patch.getId(), patch.getVid(), patch.getContent(), patch.getType());
        loadPatches();
        closeModal();
    }

    public void deletePatch(Integer id, Integer vid) {
        HubService.getPatchService().deletePatch(id, vid);
        loadPatches();
        closeModal();
    }


    public void closeModal() {
        ProjectModel.setSwitchProjectModal(false);
        ProjectModel.setSwitchCreatePatchProjectModal(false);
        ProjectModel.setSwitchEditPatchProjectModal(false);
    }
}
