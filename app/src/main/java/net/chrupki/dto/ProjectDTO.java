package net.chrupki.dto;

public class ProjectDTO {
    private final String name;
    private final String description;

    public ProjectDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProjectDTO(String name) {
        this(name, "");
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
