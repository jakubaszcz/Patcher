package net.chrupki.request;

import net.chrupki.ui.views.pages.project.dto.PatchDTO;

import java.nio.file.Path;
import java.util.List;

public record ExportRequest(
    String project,
    String version,
    String format,
    List<PatchDTO> patches,
    Path exportPath
) {}