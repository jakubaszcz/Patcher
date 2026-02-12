package net.chrupki.ui.view.pages.projects.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.CardTheme;
import net.chrupki.ui.styles.theme.TextTheme;
import net.chrupki.ui.view.pages.project.ProjectView;
import net.chrupki.ui.view.pages.projects.dto.ProjectContainerDTO;


public class ProjectsContainer extends HBox {


    public ProjectsContainer(
            ProjectContainerDTO projectContainerModel,
            Runnable onEditProjectModal
    ) {

        Label title = new Label(projectContainerModel.getProjectDTO().getName());
        new Styles().apply(title, TextTheme.TEXT_ITEM);


        Button edit = new Button("Edit");
        new Styles().apply(edit, ButtonTheme.EDIT);

        HBox actions = new HBox(edit);
        actions.setAlignment(Pos.CENTER_RIGHT);
        actions.setSpacing(6);

        getChildren().addAll(title, actions);

        new Styles().apply(this, CardTheme.DYNAMIC);

        setPadding(new Insets(10, 14, 10, 14));
        setSpacing(8);

        setOnMouseClicked(e -> {
            projectContainerModel.getViewManager().show(new ProjectView(projectContainerModel.getViewManager()));
            ProjectDTO projectDTO = new ProjectDTO(
                    projectContainerModel.getProjectDTO().getName()
            );
            HubModel.projectModel().from(projectDTO);
            HubController.getTagController().load();
        });

        edit.setOnAction(e -> {
            onEditProjectModal.run();
            ProjectDTO projectDTO = new ProjectDTO(
                    projectContainerModel.getProjectDTO().getName()
            );
            HubModel.projectModel().from(projectDTO);
        });

        setPrefSize(projectContainerModel.getWidth(), projectContainerModel.getHeight());
        setMinSize(projectContainerModel.getWidth(), projectContainerModel.getHeight());
        setMaxSize(projectContainerModel.getWidth(), projectContainerModel.getHeight());
    }
}
