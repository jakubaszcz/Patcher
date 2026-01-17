package net.chrupki.ui.views;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ProjectsView extends VBox {

    public ProjectsView(ViewManager viewManager) {

        Button button = new Button("Go to version");

        button.setOnAction(e -> {
            viewManager.show(new ProjectView(viewManager));
        });

        this.getChildren().add(button);

    }

}
