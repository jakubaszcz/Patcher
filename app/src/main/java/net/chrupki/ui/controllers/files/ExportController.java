package net.chrupki.ui.controllers.files;

import net.chrupki.project.services.HubService;
import net.chrupki.project.services.files.exports.MarkdownExportService;
import net.chrupki.request.ExportRequest;
import net.chrupki.ui.model.GlobalModel;

public class ExportController {


    public void export(ExportRequest request) {
        System.out.println("Exporting to " + request.exportPath());
        if (request.format().equals("markdown")) {
            HubService.getMarkdownExportService().exportMarkdown(request);
        }
    }

    public void closeModal() {
        GlobalModel.setSwitchProjectModal(false);
        GlobalModel.setSwitchExportModal(false);
    }
}
