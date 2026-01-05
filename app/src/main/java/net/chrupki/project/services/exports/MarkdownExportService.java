package net.chrupki.project.services.exports;

import net.chrupki.request.ExportRequest;

import java.io.IOException;
import java.nio.file.Files;

public class MarkdownExportService {

    public void exportMarkdown(ExportRequest request) {
        String markdown = generateMarkdown(request);
        try {
            Files.writeString(request.exportPath(), markdown);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateMarkdown(ExportRequest request) {
        StringBuilder md = new StringBuilder();

        md.append("# Project ").append(request.project()).append(" (v.").append(request.version()).append(")\n\n");

        return md.toString();
    }
}
