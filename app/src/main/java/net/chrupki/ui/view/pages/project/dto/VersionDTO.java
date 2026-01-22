package net.chrupki.ui.view.pages.project.dto;

public class VersionDTO {
    private final int id;
    private final String version;

    public VersionDTO(int id, String version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }
}
