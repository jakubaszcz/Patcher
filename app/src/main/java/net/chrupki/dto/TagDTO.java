package net.chrupki.dto;

public class TagDTO {

    private final String name;
    private final int id;

    public TagDTO(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public int getId() {return id; }

}
