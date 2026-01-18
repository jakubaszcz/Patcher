package net.chrupki.ui.views.pages.project;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.manager.ViewManager;
import net.chrupki.ui.views.pages.projects.ProjectsView;

public class ProjectView extends VBox {

    public ProjectView(ViewManager viewManager) {

        Button button = new Button("Go to project");

        button.setOnAction(e -> {
            viewManager.show(new ProjectsView(viewManager));
        });

        this.getChildren().add(button);

    }

}
