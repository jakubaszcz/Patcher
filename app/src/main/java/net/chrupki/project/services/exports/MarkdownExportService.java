package net.chrupki.project.services.exports;

import net.chrupki.request.ExportRequest;

import java.io.IOException;
import java.nio.file.Files;

public class MarkdownExportService {

    public void exportMarkdown(ExportRequest request) throws IOException {
        String markdown = generateMarkdown(request);
        Files.writeString(request.exportPath(), markdown);
    }

    public String generateMarkdown(ExportRequest request) {
        // Generate markdown
        return "";
    }
}
