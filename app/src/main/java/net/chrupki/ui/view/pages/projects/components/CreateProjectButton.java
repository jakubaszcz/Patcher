package net.chrupki.ui.view.pages.projects.components;

import javafx.scene.layout.HBox;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextTheme;
import net.chrupki.ui.view.pages.projects.dto.CreateProjectButtonDTO;
import javafx.scene.control.Label;


public class CreateProjectButton extends HBox {

    public CreateProjectButton(
            CreateProjectButtonDTO model,
            Runnable onCreateProjectModal
    ) {

        Label label = new Label("+");
        new Styles().apply(label, TextTheme.TEXT_ITEM);


        getChildren().add(label);
        new Styles().apply(this, ButtonTheme.NORMAL);

        setOnMouseClicked(e -> {
            onCreateProjectModal.run();
        });

        setAlignment(javafx.geometry.Pos.CENTER);
        setPrefSize(model.getWidth(), model.getHeight());
        setMinSize(model.getWidth(), model.getHeight());
        setMaxSize(model.getWidth(), model.getHeight());
    }

}
