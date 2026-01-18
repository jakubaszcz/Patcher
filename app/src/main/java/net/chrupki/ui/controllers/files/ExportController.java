package net.chrupki.ui.controllers.files;

import net.chrupki.project.services.HubService;
import net.chrupki.project.services.files.exports.MarkdownExportService;
import net.chrupki.request.ExportRequest;

public class ExportController {


    public void export(ExportRequest request) {
        System.out.println("Exporting to " + request.exportPath());
        switch (request.format()) {
            case "markdown":
                HubService.getMarkdownExportService().exportMarkdown(request);
                break;
        }
    }
}
