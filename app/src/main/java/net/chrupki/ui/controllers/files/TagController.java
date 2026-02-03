package net.chrupki.ui.controllers.files;

import net.chrupki.dto.TagDTO;
import net.chrupki.dto.VersionDTO;
import net.chrupki.model.HubModel;
import net.chrupki.project.services.HubService;
import net.chrupki.project.services.files.TagService;
import net.chrupki.ui.model.GlobalModel;

import java.io.IOException;

public class TagController {

    public void create(String tagName) {

        try {
            TagDTO tag =  HubService.getTagService().create(tagName);
            GlobalModel.getTags().add(tag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

}
