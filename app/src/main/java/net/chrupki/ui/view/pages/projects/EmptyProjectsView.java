package net.chrupki.ui.view.pages.projects;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextTheme;

public class EmptyProjectsView extends VBox {

    public EmptyProjectsView(Runnable onCreateProjectModal) {

        Label title = new Label("Welcome to Patcher");
        new Styles().apply(title, TextTheme.TITLE);

        Label subtitle = new Label(
                "It seems you don’t have any project yet.\nLet’s create your first one."
        );
        new Styles().apply(subtitle, TextTheme.SUBTITLE);


        Button createButton = new Button("+ Create project");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

        createButton.setOnAction(e -> onCreateProjectModal.run());

        setAlignment(Pos.CENTER);
        setSpacing(18);

        getChildren().addAll(
                title,
                subtitle,
                createButton
        );
    }
}
