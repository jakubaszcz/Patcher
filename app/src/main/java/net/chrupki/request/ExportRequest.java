package net.chrupki.request;

import net.chrupki.ui.view.pages.project.dto.PatchDTO;

import java.nio.file.Path;
import java.util.List;

public record ExportRequest(
    String project,
    String version,
    String type,
    String format,
    List<PatchDTO> patches,
    Path exportPath
) {}