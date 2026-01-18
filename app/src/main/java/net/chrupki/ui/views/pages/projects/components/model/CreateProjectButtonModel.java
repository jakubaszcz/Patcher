package net.chrupki.ui.views.pages.projects.components.model;

public class CreateProjectButtonModel {
    private final double width;
    private final double height;

    public CreateProjectButtonModel(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
