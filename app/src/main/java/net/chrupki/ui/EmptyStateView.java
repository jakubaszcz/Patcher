package net.chrupki.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.IconTheme;
import net.chrupki.ui.styles.theme.TextTheme;
import org.kordamp.ikonli.javafx.FontIcon;

public class EmptyStateView extends VBox {

    public EmptyStateView(String title, String message, Runnable onClick) {


        Label text_title = new Label(title);
        text_title.setMaxWidth(Double.MAX_VALUE);
        text_title.setAlignment(Pos.CENTER);
        text_title.setTextAlignment(TextAlignment.CENTER);
        text_title.setWrapText(true);
        new Styles().apply(text_title, TextTheme.TITLE);

        Label text_message = new Label(message);
        text_message.setMaxWidth(Double.MAX_VALUE);
        text_message.setAlignment(Pos.CENTER);
        text_message.setTextAlignment(TextAlignment.CENTER);
        text_message.setWrapText(true);
        new Styles().apply(text_message, TextTheme.TEXT_MUTED);

        FontIcon icon = new FontIcon("fas-plus");
        new Styles().apply(icon, IconTheme.PRIMARY);

        Button button = new Button("Create " + title.toLowerCase(), icon);
        new Styles().apply(button, ButtonTheme.NORMAL);

        button.setOnAction(e -> onClick.run());

        setAlignment(Pos.CENTER);
        setSpacing(18);
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);

        getChildren().addAll(
                text_title,
                text_message,
                button
        );

    }

}
