package net.chrupki.app;

import net.chrupki.app.context.PatchContext;
import net.chrupki.app.context.ProjectContext;
import net.chrupki.app.context.VersionContext;

public class AppContext {

    private static final ProjectContext projectContext = new ProjectContext();
    private static final VersionContext versionContext = new VersionContext();
    private static final PatchContext patchContext = new PatchContext();

    public static ProjectContext projectContext() {
        return projectContext;
    }

    public static VersionContext versionContext() {
        return versionContext;
    }

    public static PatchContext patchContext() {
        return patchContext;
    }

}
