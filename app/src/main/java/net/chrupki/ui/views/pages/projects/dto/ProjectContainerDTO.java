package net.chrupki.ui.views.pages.projects.dto;

import net.chrupki.ui.views.manager.ViewManager;

public class ProjectContainerDTO {

    private final ViewManager viewManager;

    private final String text;
    private final double width;
    private final double height;

    public ProjectContainerDTO(ViewManager viewManager, String text, double width, double height) {
        this.viewManager = viewManager;
        this.text = text;
        this.width = width;
        this.height = height;
    }

    public ViewManager getViewManager() { return viewManager; }

    public String getText() { return text; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
