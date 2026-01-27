package net.chrupki.ui.controllers.files;

import net.chrupki.project.services.HubService;
import net.chrupki.ui.model.GlobalModel;

import java.io.IOException;

public class TemplateController {

    public void loadTemplates() {
        try {
            GlobalModel.getTemplates().setAll(
                    HubService.getTemplateService().fetchAllTemplates()
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
