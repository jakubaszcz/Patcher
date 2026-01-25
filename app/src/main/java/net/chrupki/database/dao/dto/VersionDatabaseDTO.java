package net.chrupki.database.dao.dto;

public class VersionDatabaseDTO {

    private final String version;
    private final String type;

    public VersionDatabaseDTO(String version, String type) {
        this.version = version;
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }
}
