package net.chrupki.ui.view.manager;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class PageManager {

    private final StackPane container = new StackPane();

    public void show(Parent view) {
        container.getChildren().clear();
        container.getChildren().add(view);
    }

    public StackPane getContainer() {
        return container;
    }

}
