package net.chrupki.ui.view.pages.projects.dto;

import net.chrupki.dto.ProjectDTO;
import net.chrupki.ui.view.manager.PageManager;

public class ProjectContainerDTO {

    private final PageManager viewManager;

    private final ProjectDTO projectDTO;

    private final double width;
    private final double height;

    public ProjectContainerDTO(PageManager viewManager, ProjectDTO projectDTO, double width, double height) {
        this.viewManager = viewManager;
        this.projectDTO = projectDTO;
        this.width = width;
        this.height = height;
    }

    public PageManager getViewManager() { return viewManager; }

    public ProjectDTO getProjectDTO() { return projectDTO; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
