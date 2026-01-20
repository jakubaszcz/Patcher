package net.chrupki.ui.views.pages.project;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.manager.ViewManager;
import net.chrupki.ui.views.pages.project.components.Body;
import net.chrupki.ui.views.pages.project.components.Header;
import net.chrupki.ui.views.pages.project.modals.ProjectModal;
import net.chrupki.ui.views.pages.projects.ProjectsView;

public class ProjectView extends StackPane {

    public ProjectView(ViewManager viewManager) {

        Header header = new Header(viewManager);
        Body body = new Body();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(header);
        borderPane.setCenter(body);

        VBox.setVgrow(borderPane, Priority.ALWAYS);

        getChildren().add(borderPane);

        // Modal
        getChildren().add(
                new ProjectModal()
        );
    }
}

