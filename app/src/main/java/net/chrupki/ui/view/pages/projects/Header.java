package net.chrupki.ui.view.pages.projects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import net.chrupki.app.AppPath;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.awt.*;
import java.io.File;

public class Header extends VBox {

    public Header() {

        HBox container = new HBox();
        VBox contentLeft = new VBox();
        VBox contentRight = new VBox();

        {
            Label title = new Label("Patcher");
            new Styles().apply(title, TextTheme.TITLE);

            Label subtitle = new Label("Manage your software versions and patches");
            new Styles().apply(subtitle, TextTheme.TEXT_MUTED);

            contentLeft.getChildren().addAll(title, subtitle);
            contentLeft.setAlignment(Pos.CENTER_LEFT);
        }

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        {
            Label templateTitle = new Label("Manage your templates");
            new Styles().apply(templateTitle, TextTheme.TEXT_MUTED);


            Button openTemplate = new Button("Templates");
            new Styles().apply(openTemplate, ButtonTheme.NORMAL);

            contentRight.getChildren().addAll(templateTitle, openTemplate);
            contentRight.setAlignment(Pos.CENTER_RIGHT);

            openTemplate.setOnAction(e -> {
                File directory = new File(AppPath.getDataDir().resolve("templates").toString());
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(directory);
                    }
                    else {
                        System.out.println("Desktop not supported");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }

        container.getChildren().addAll(contentLeft, spacer, contentRight);

        setSpacing(4);
        setPadding(new Insets(20, 20, 10, 20));
        setAlignment(Pos.CENTER_LEFT);

        new Styles().apply(this, ContainerTheme.HEADER);

        getChildren().addAll(container);
    }

}
