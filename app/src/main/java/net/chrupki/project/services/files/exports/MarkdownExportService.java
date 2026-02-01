package net.chrupki.project.services.files.exports;

import net.chrupki.database.dao.PatchDAO;
import net.chrupki.project.AppProject;
import net.chrupki.request.ExportRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownExportService {

    public void exportMarkdown(ExportRequest request) {
        String markdown = generateMarkdown(request);
        try {
            Files.writeString(request.exportPath(), markdown);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getType(String type) {
        return switch (type) {
            case "Alpha" -> "-alpha";
            case "Beta" -> "-beta";
            case "Pre-Release" -> "-pre";
            case "HotFix" -> "hotfix";
            default -> "";
        };
    }

    public String getPatch(ExportRequest request) {

        StringBuilder md = new StringBuilder();

        List<String> notes = List.of("Patch", "Add", "Features", "Fix");

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

    public String generateDefaultMarkdown(ExportRequest request) {
        StringBuilder md = new StringBuilder();

        md.append("# Project ").append(request.project()).append(" (v.").append(request.version()).append(getType(request.type())).append(")\n\n");

        md.append(getPatch(request));

        return md.toString();
    }

    private static final Pattern VAR_PATTERN = Pattern.compile("§(\\w+)");

    public String generateCustomMarkdown(ExportRequest request) {
        try {
            String template = AppProject.fetchTemplateContent(request.templates());
            Matcher matcher = VAR_PATTERN.matcher(template);

            StringBuilder result = new StringBuilder();

            while (matcher.find()) {
                String key = matcher.group(1);
                String replacement = resolveVariable(key, request);
                matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
            }
            matcher.appendTail(result);
            return result.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String resolveVariable(String key, ExportRequest request) {
        return switch (key) {
            case "project" -> request.project();
            case "version" -> request.version() + getType(request.type());
            case "date" -> LocalDate.now().toString();
            case "patch" -> getPatch(request);
            default -> "";
        };
    }

    public String generateMarkdown(ExportRequest request) {

        if (request.templates().isEmpty() || request.templates().equals("default")) return generateDefaultMarkdown(request);
        else return generateCustomMarkdown(request);
    }
}
