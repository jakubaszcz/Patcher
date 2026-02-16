package net.chrupki.ui.view.pages.projects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

public class Header extends VBox {

    public Header() {
        Label title = new Label("Patcher");
        new Styles().apply(title, TextTheme.TITLE);

        Label subtitle = new Label("Manage your software versions and patches");
        new Styles().apply(subtitle, TextTheme.TEXT_MUTED);

        setSpacing(4);
        setPadding(new Insets(20, 20, 10, 20));
        setAlignment(Pos.CENTER_LEFT);

        new Styles().apply(this, ContainerTheme.HEADER);

        getChildren().addAll(title, subtitle);
    }

}
