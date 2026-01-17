package net.chrupki.ui.views;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ViewManager {

    private final StackPane container = new StackPane();

    public void show(Parent view) {
        container.getChildren().add(view);
    }

    public StackPane getContainer() {
        return container;
    }

}
