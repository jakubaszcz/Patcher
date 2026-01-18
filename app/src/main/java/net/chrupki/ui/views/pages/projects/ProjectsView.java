package net.chrupki.ui.views.pages.projects;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.manager.ViewManager;
import net.chrupki.ui.views.pages.project.ProjectView;
import net.chrupki.ui.views.pages.projects.components.CreateProjectButton;
import net.chrupki.ui.views.pages.projects.components.ProjectsContainer;
import net.chrupki.ui.views.pages.projects.components.model.CreateProjectButtonModel;
import net.chrupki.ui.views.pages.projects.components.model.ProjectContainerModel;
import net.chrupki.ui.views.pages.projects.modals.ProjectsModal;

public class ProjectsView extends StackPane {

    private final double WIDTH = 280;
    private final double HEIGHT = 72;
    private final double gap = 6;

    private final FlowPane projectsView = new FlowPane();
    private final ViewManager viewManager;

    private final CreateProjectButton createButton;

    public ProjectsView(ViewManager viewManager) {
        this.viewManager = viewManager;

        ObservableList<String> projects = ProjectModel.getProjects();

        projectsView.setHgap(gap);
        projectsView.setVgap(gap);
        projectsView.setPadding(new Insets(16));
        projectsView.setAlignment(Pos.TOP_CENTER);

        createButton = new CreateProjectButton(
                new CreateProjectButtonModel(WIDTH, HEIGHT),
                HubController.getProjectController()::openCreateProjectsModal

        );

        projectsView.widthProperty().addListener((obs, oldW, newW) ->
                updateWrapLength(newW.doubleValue())
        );

        refresh(projects);

        projects.addListener((ListChangeListener<String>) c ->
                refresh(projects)
        );

        getChildren().add(projectsView);

        // Modal
        getChildren().add(
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

        projectsView.getChildren().add(createButton);

        for (String p : projects) {
            projectsView.getChildren().add(
                    new ProjectsContainer(
                            new ProjectContainerModel(
                                    viewManager,
                                    p,
                                    WIDTH,
                                    HEIGHT
                            )
                    )
            );
        }
    }
}

