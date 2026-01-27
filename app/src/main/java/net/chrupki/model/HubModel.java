package net.chrupki.model;

import net.chrupki.model.files.PatchModel;
import net.chrupki.model.files.ProjectModel;
import net.chrupki.model.files.VersionModel;

public class HubModel {

    private static final ProjectModel projectModel = new ProjectModel();
    private static final VersionModel versionModel = new VersionModel();
    private static final PatchModel patchModel = new PatchModel();

    public static ProjectModel projectModel() {
        return projectModel;
    }
    public static VersionModel versionModel() { return versionModel; }
    public static PatchModel patchModel() {
        return patchModel;
    }
}
