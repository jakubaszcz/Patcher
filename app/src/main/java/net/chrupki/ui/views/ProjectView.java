package net.chrupki.ui.views;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ProjectView extends VBox {

    public ProjectView(ViewManager viewManager) {

        Button button = new Button("Go to project");

        button.setOnAction(e -> {
            viewManager.show(new ProjectsView(viewManager));
        });

        this.getChildren().add(button);

    }

}
