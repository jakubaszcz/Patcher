package net.chrupki.ui.view.pages.projects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

public class Footer extends VBox {

    public Footer() {
        Label copyright = new Label("© 2026 Patcher - All rights reserved.");
        new Styles().apply(copyright, TextTheme.TEXT_MUTED);

        setPadding(new Insets(10, 20, 10, 20));
        setAlignment(Pos.CENTER);

        new Styles().apply(this, ContainerTheme.HEADER);

        getChildren().addAll(copyright);
    }

}
