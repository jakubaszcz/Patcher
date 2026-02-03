package net.chrupki.ui.controllers.files;

import net.chrupki.dto.TagDTO;
import net.chrupki.dto.VersionDTO;
import net.chrupki.model.HubModel;
import net.chrupki.project.services.HubService;
import net.chrupki.project.services.files.TagService;
import net.chrupki.ui.controllers.files.dtos.EditPatch;
import net.chrupki.ui.controllers.files.dtos.EditTag;
import net.chrupki.ui.model.GlobalModel;

import java.io.IOException;

public class TagController {

    public void create(TagDTO tagDTO) {

        try {
            TagDTO tag =  HubService.getTagService().create(tagDTO.getName());
            GlobalModel.getTags().add(tag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        load();
    }

    public void load() {
        try {
            GlobalModel.getTags().setAll(
                    HubService.getTagService().fetch(HubModel.projectModel().getName().get())
            );
        } catch (IOException e) {

            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(EditTag tag) {
        HubService.getTagService().save(tag);
        load();
        closeModal();
    }

    public void delete(Integer id) {
        HubService.getTagService().delete(id);
        load();
    }

    public void closeModal() {
        GlobalModel.setSwitchProjectModal(false);
        GlobalModel.setSwitchCreateTagProjectModal(false);
        GlobalModel.setSwitchEditTagProjectModal(false);
    }

}
