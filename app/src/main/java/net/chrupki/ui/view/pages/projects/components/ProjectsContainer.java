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
import net.chrupki.ui.modal.ConfirmModal;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.CardTheme;
import net.chrupki.ui.styles.theme.IconTheme;
import net.chrupki.ui.styles.theme.TextTheme;
import net.chrupki.ui.view.pages.project.ProjectView;
import net.chrupki.ui.view.pages.projects.dto.ProjectContainerDTO;
import org.kordamp.ikonli.javafx.FontIcon;


public class ProjectsContainer extends VBox {


    public ProjectsContainer(
            ProjectContainerDTO projectContainerModel,
            Runnable onEditProjectModal
    ) {

        FontIcon editIcon = new FontIcon("fas-pen");
        new Styles().apply(editIcon, IconTheme.EDIT);
        editIcon.setPickOnBounds(true);

        FontIcon trashIcon = new FontIcon("fas-trash");
        new Styles().apply(trashIcon, IconTheme.EDIT);
        trashIcon.setPickOnBounds(true);


        int descLimit = 200;

        Label title = new Label(projectContainerModel.getProjectDTO().getName());
        new Styles().apply(title, TextTheme.SUBTITLE);

        Label description = new Label();
        description.setWrapText(true);
        description.setMaxWidth(projectContainerModel.getWidth() - 28);

        String descriptionText = projectContainerModel.getProjectDTO().getDescription();
        if (descriptionText == null || descriptionText.isBlank()) {
            descriptionText = "no description found";
            new Styles().apply(description, TextTheme.TEXT_MUTED);
        } else if (descriptionText.length() > descLimit) {
            descriptionText = "description too long (max " + descLimit + " chars)";
            new Styles().apply(description, TextTheme.TEXT_MUTED);
        }
        new Styles().apply(description, TextTheme.TEXT_MAIN);
        description.setText(descriptionText);

        HBox horizontal = new HBox();

        Button edit = new Button(null, editIcon);
        new Styles().apply(edit, ButtonTheme.EDIT);

        Button delete = new Button(null, trashIcon);
        new Styles().apply(delete, ButtonTheme.EDIT);

        horizontal.getChildren().addAll(edit, delete);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox header = new HBox(title, spacer, horizontal);
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
            HubModel.projectModel().from(projectContainerModel.getProjectDTO());
            onEditProjectModal.run();
        });

        delete.setOnAction(e -> {
            HubModel.projectModel().from(projectContainerModel.getProjectDTO());
            ConfirmModal.changeType(ConfirmModal.Type.Project);
            GlobalModel.setSwitchProjectsModal(true);
            GlobalModel.setSwitchConfirmModal(true);
        });

        setPrefWidth(projectContainerModel.getWidth());
        setMinWidth(projectContainerModel.getWidth());
        setMaxWidth(projectContainerModel.getWidth());
        setMinHeight(projectContainerModel.getHeight());
    }
}
