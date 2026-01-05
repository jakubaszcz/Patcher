package net.chrupki.request;

import net.chrupki.model.Patch;

import java.nio.file.Path;
import java.util.List;

public record ExportRequest(
    String project,
    String version,
    String format,
    List<Patch> patches,
    Path exportPath
) {}