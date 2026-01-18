package net.chrupki.ui.views.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Header extends HBox {

    public Header() {

        Label title = new Label("Patcher");
        title.getStyleClass().add("header-title");

        setSpacing(12);
        setPadding(new Insets(10));

        getStyleClass().add("header");
        getChildren().addAll(title);
    }
}
