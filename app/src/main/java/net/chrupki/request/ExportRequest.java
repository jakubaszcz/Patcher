package net.chrupki.request;

import net.chrupki.dto.PatchDTO;

import java.nio.file.Path;
import java.util.List;

public record ExportRequest(
    String project,
    String version,
    String type,
    String format,
    String templates,
    List<PatchDTO> patches,
    Path exportPath
) {}