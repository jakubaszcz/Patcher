package net.chrupki.model;

public class Version {
    private final int id;
    private final String version;

    public Version(int id, String version) {
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
