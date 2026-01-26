package net.chrupki.project.services.files.exports;

import net.chrupki.database.dao.PatchDAO;
import net.chrupki.request.ExportRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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

        List<String> notes = List.of("Patch", "Add", "Features", "Fix");

        System.out.println(request.type());

        String type = switch (request.type()) {
            case "Alpha" -> "alpha";
            case "Beta" -> "beta";
            case "Pre-Release" -> "pre-release";
            case "HotFix" -> "hotfix";
            default -> null;
        };

        md.append("# Project ").append(request.project()).append(" (v.").append(request.version()).append("-").append(type).append(")\n\n");

        for (String note : notes) {
            if (PatchDAO.findByType(request.project(), note).isEmpty()) continue;
            md.append("## ").append(note).append("\n\n");
            for (String patch : PatchDAO.findByType(request.project(), note)) {
                md.append("- ").append(patch).append("\n");
            }
            md.append("\n");
        }

        return md.toString();
    }
}
