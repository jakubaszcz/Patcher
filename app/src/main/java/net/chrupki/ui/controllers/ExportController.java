package net.chrupki.ui.controllers;

import net.chrupki.project.services.exports.MarkdownExportService;
import net.chrupki.request.ExportRequest;

import java.nio.file.Path;

public class ExportController {

    private MarkdownExportService markdownExportService;

    public ExportController(MarkdownExportService markdownExportService) {
        this.markdownExportService = markdownExportService;
    }

    public void export(ExportRequest request) {
        switch (request.format()) {
            case "markdown":
                markdownExportService.exportMarkdown(request);
                break;
        }
    }
}
