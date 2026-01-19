package net.chrupki.ui.views.pages.project;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.manager.ViewManager;
import net.chrupki.ui.views.pages.project.components.Header;
import net.chrupki.ui.views.pages.projects.ProjectsView;

public class ProjectView extends VBox {

    public ProjectView(ViewManager viewManager) {

        BorderPane borderPane = new BorderPane();

        Header header = new Header(viewManager);

        borderPane.setTop(header);

        this.getChildren().add(borderPane);
    }

}
