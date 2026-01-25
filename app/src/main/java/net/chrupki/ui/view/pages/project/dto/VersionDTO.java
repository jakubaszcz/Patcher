package net.chrupki.ui.view.pages.project.dto;

public class VersionDTO {
    private final int id;
    private final String version;
    private final String type;

    public VersionDTO(int id, String version, String type) {
        this.id = id;
        this.version = version;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }
    public String getType() { return type; }
}
