package net.chrupki.ui.view.pages.projects;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.view.manager.PageManager;
import net.chrupki.ui.view.pages.projects.components.CreateProjectButton;
import net.chrupki.ui.view.pages.projects.components.ProjectsContainer;
import net.chrupki.ui.view.pages.projects.dto.CreateProjectButtonDTO;
import net.chrupki.ui.view.pages.projects.dto.ProjectContainerDTO;
import net.chrupki.ui.view.pages.projects.modals.ProjectsModal;

public class ProjectsView extends StackPane {

    private final double WIDTH = 280;
    private final double HEIGHT = 72;
    private final double gap = 6;

    private final FlowPane projectsView = new FlowPane();
    private final ScrollPane scrollPane = new ScrollPane();

    private final PageManager viewManager;
    private final CreateProjectButton createButton;

    public ProjectsView(PageManager viewManager) {
        this.viewManager = viewManager;

        ObservableList<String> projects = ProjectModel.getProjects();

        projectsView.setHgap(gap);
        projectsView.setVgap(gap);
        projectsView.setPadding(new Insets(gap));
        projectsView.setAlignment(Pos.TOP_CENTER);

        scrollPane.setContent(projectsView);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStyleClass().add("invisible-scroll");

        scrollPane.viewportBoundsProperty().addListener((obs, oldB, newB) ->
                updateWrapLength(newB.getWidth())
        );

        createButton = new CreateProjectButton(
                new CreateProjectButtonDTO(WIDTH, HEIGHT),
                HubController.getProjectController()::openCreateProjectsModal
        );

        refresh(projects);

        projects.addListener((ListChangeListener<String>) c ->
                refresh(projects)
        );

        getChildren().addAll(
                scrollPane,
                new ProjectsModal()
        );
    }

    private void updateWrapLength(double availableWidth) {

        int columns = Math.max(
                1,
                (int) ((availableWidth + gap) / (WIDTH + gap))
        );

        double wrapLength =
                (columns * WIDTH) + ((columns - 1) * gap);

        projectsView.setPrefWrapLength(wrapLength);
    }

    private void refresh(ObservableList<String> projects) {

        projectsView.getChildren().clear();

        if (projects.isEmpty()) {
            projectsView.getChildren().add(
                    new EmptyProjectsView(
                            HubController.getProjectController()::openCreateProjectsModal
                    )
            );
            return;
        }

        projectsView.getChildren().add(createButton);

        for (String p : projects) {
            projectsView.getChildren().add(
                    new ProjectsContainer(
                            new ProjectContainerDTO(
                                    viewManager,
                                    p,
                                    WIDTH,
                                    HEIGHT
                            ),
                            HubController.getProjectController()::openEditProjectsModal
                    )
            );
        }
    }
}
