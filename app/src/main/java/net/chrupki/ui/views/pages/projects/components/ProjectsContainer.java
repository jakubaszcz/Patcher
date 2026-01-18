package net.chrupki.ui.views.pages.projects.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import net.chrupki.app.AppContext;
import net.chrupki.ui.views.pages.project.ProjectView;
import net.chrupki.ui.views.pages.projects.components.model.ProjectContainerModel;


public class ProjectsContainer extends HBox {


    public ProjectsContainer(
            ProjectContainerModel projectContainerModel,
            Runnable onEditProjectModal
    ) {

        Label title = new Label(projectContainerModel.getText());
        title.getStyleClass().add("project-card-title");

        Button edit = new Button("Edit");
        edit.getStyleClass().add("project-card-action");

        HBox actions = new HBox(edit);
        actions.getStyleClass().add("project-card-actions");

        getChildren().addAll(title, actions);

        getStyleClass().add("project-card");

        setPadding(new Insets(10, 14, 10, 14));
        setSpacing(8);

        setOnMouseClicked(e -> {
            projectContainerModel.getViewManager().show(new ProjectView(projectContainerModel.getViewManager()));
        });

        edit.setOnAction(e -> {
            onEditProjectModal.run();
            AppContext.projectContext().setName(projectContainerModel.getText());
        });

        setPrefSize(projectContainerModel.getWidth(), projectContainerModel.getHeight());
        setMinSize(projectContainerModel.getWidth(), projectContainerModel.getHeight());
        setMaxSize(projectContainerModel.getWidth(), projectContainerModel.getHeight());
    }
}
