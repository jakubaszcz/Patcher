package net.chrupki.ui.controllers.dtos;

public class EditVersion {

    private final Integer id;
    private final String newName;
    private final String project;

    public EditVersion(Integer id, String newName, String project) {
        this.id = id;
        this.newName = newName;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public String getNewName() {
        return newName;
    }

    public String getProject() {
        return  project;
    }

}
