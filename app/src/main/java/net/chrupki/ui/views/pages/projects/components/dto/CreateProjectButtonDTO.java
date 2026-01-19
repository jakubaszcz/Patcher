package net.chrupki.ui.views.pages.projects.components.dto;

public class CreateProjectButtonDTO {
    private final double width;
    private final double height;

    public CreateProjectButtonDTO(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
