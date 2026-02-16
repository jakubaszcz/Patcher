package net.chrupki.ui.view.pages.projects;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.scroll.ScrollStyle;
import net.chrupki.ui.view.manager.PageManager;
import net.chrupki.ui.view.pages.projects.components.CreateProjectButton;
import net.chrupki.ui.view.pages.projects.components.ProjectsContainer;
import net.chrupki.ui.view.pages.projects.dto.CreateProjectButtonDTO;
import net.chrupki.ui.view.pages.projects.dto.ProjectContainerDTO;
import net.chrupki.ui.view.pages.projects.modals.ProjectsModal;

public class ProjectsView extends StackPane {

    private final double WIDTH = 280;
    private final double HEIGHT = 110;
    private final double gap = 6;

    private final HBox projectsView = new HBox(gap);
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox container = new VBox();

    private final PageManager viewManager;
    private final CreateProjectButton createButton;

    public ProjectsView(PageManager viewManager) {
        this.viewManager = viewManager;

        ObservableList<ProjectDTO> projects = GlobalModel.getProjects();

        projectsView.setPadding(new Insets(gap));
        projectsView.setAlignment(Pos.TOP_CENTER);

        scrollPane.setContent(projectsView);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        new ScrollStyle().apply(scrollPane, true);

        scrollPane.viewportBoundsProperty().addListener((obs, oldB, newB) -> {
            if (oldB == null || Math.abs(oldB.getWidth() - newB.getWidth()) > 1) {
                refresh(projects);
            }
        });

        createButton = new CreateProjectButton(
                new CreateProjectButtonDTO(WIDTH, HEIGHT),
                HubController.getProjectController()::openCreateProjectsModal
        );

        refresh(projects);

        projects.addListener((ListChangeListener<ProjectDTO>) c ->
                refresh(projects)
        );

        container.getChildren().addAll(new Header(), scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        getChildren().addAll(
                container,
                new ProjectsModal()
        );
    }

    private int getColumnCount(double availableWidth) {
        return Math.max(1, (int) ((availableWidth - (2 * gap) + gap) / (WIDTH + gap)));
    }

    private void refresh(ObservableList<ProjectDTO> projects) {
        projectsView.getChildren().clear();

        if (projects.isEmpty()) {
            projectsView.getChildren().add(
                    new EmptyProjectsView(
                            HubController.getProjectController()::openCreateProjectsModal
                    )
            );
            return;
        }

        double availableWidth = scrollPane.getViewportBounds().getWidth();
        if (availableWidth <= 0) {
            availableWidth = scrollPane.getWidth();
        }

        int columns = getColumnCount(availableWidth);
        VBox[] columnNodes = new VBox[columns];

        for (int i = 0; i < columns; i++) {
            columnNodes[i] = new VBox(gap);
            columnNodes[i].setAlignment(Pos.TOP_CENTER);
            columnNodes[i].setMinWidth(WIDTH);
            columnNodes[i].setMaxWidth(WIDTH);
            projectsView.getChildren().add(columnNodes[i]);
        }

        columnNodes[0].getChildren().add(createButton);

        int currentColumn = 1;
        for (ProjectDTO p : projects) {
            columnNodes[currentColumn % columns].getChildren().add(
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
            currentColumn++;
        }
    }
}
