package net.chrupki.ui.view.pages.projects.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.CardTheme;
import net.chrupki.ui.styles.theme.TextTheme;
import net.chrupki.ui.view.pages.project.ProjectView;
import net.chrupki.ui.view.pages.projects.dto.ProjectContainerDTO;


public class ProjectsContainer extends VBox {


    public ProjectsContainer(
            ProjectContainerDTO projectContainerModel,
            Runnable onEditProjectModal
    ) {

        int descLimit = 200;

        Label title = new Label(projectContainerModel.getProjectDTO().getName());
        new Styles().apply(title, TextTheme.SUBTITLE);

        String descriptionText = projectContainerModel.getProjectDTO().getDescription();
        if (descriptionText == null || descriptionText.isBlank()) {
            descriptionText = "no description found";
        } else if (descriptionText.length() > descLimit) {
            descriptionText = "description too long (max " + descLimit + " chars)";
        }

        Label description = new Label(descriptionText);
        description.setWrapText(true);
        description.setMaxWidth(projectContainerModel.getWidth() - 28);
        new Styles().apply(description, TextTheme.TEXT_ITEM);

        Button edit = new Button("Edit");
        new Styles().apply(edit, ButtonTheme.EDIT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox header = new HBox(title, spacer, edit);
        header.setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(header, description);

        new Styles().apply(this, CardTheme.DYNAMIC);

        setPadding(new Insets(10, 14, 10, 14));
        setSpacing(4);
        setAlignment(Pos.TOP_LEFT);

        setOnMouseClicked(e -> {
            projectContainerModel.getViewManager().show(new ProjectView(projectContainerModel.getViewManager()));
            HubModel.projectModel().from(projectContainerModel.getProjectDTO());
            HubController.getTagController().load();
        });

        edit.setOnAction(e -> {
            onEditProjectModal.run();
            HubModel.projectModel().from(projectContainerModel.getProjectDTO());
        });

        setPrefWidth(projectContainerModel.getWidth());
        setMinWidth(projectContainerModel.getWidth());
        setMaxWidth(projectContainerModel.getWidth());
        setMinHeight(projectContainerModel.getHeight());
    }
}
