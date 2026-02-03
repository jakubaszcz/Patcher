package net.chrupki.ui.controllers.files;

import net.chrupki.model.HubModel;
import net.chrupki.dto.PatchDTO;
import net.chrupki.project.services.HubService;
import net.chrupki.request.PatchRequest;
import net.chrupki.ui.controllers.files.dtos.EditPatch;
import net.chrupki.ui.model.GlobalModel;

public class PatchController {

    public void createPatch(PatchRequest request) {
        String projectName = HubModel.projectModel().getName().get();
        if (projectName == null || projectName.isBlank()) { throw new IllegalStateException("No project selected");}

        Integer versionId = HubModel.versionModel().getId().get();
        if (versionId == null) { throw new IllegalStateException("No version selected");}

        GlobalModel.getPatches().add(new PatchDTO(
                        request.name(),
                        request.tid(),
                        HubService.getPatchService().createPatch(
                                projectName,
                                versionId,
                                request.tid(),
                                request.name()
                        ),
                        request.vid()
                )
        );
    }

    public void loadPatches() {
        String projectName = HubModel.projectModel().getName().get();
        try {
            GlobalModel.getPatches().setAll(
                    HubService.getPatchService().fetchVersions(projectName)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void savePatch(EditPatch patch) {
        HubService.getPatchService().savePatch(patch.getId(), patch.getVid(), patch.getContent(), patch.getTid());
        loadPatches();
        closeModal();
    }

    public void deletePatch(Integer id, Integer vid) {
        HubService.getPatchService().deletePatch(id, vid);
        loadPatches();
        closeModal();
    }


    public void closeModal() {
        GlobalModel.setSwitchProjectModal(false);
        GlobalModel.setSwitchCreatePatchProjectModal(false);
        GlobalModel.setSwitchEditPatchProjectModal(false);
    }
}
