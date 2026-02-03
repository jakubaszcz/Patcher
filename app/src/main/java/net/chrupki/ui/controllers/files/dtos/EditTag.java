package net.chrupki.ui.controllers.files.dtos;

public class EditTag {

    private final Integer id;
    private final String name;

    public EditTag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
