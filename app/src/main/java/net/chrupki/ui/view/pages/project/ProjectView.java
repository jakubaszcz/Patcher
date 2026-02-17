package net.chrupki.ui.view.pages.project;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.view.manager.PageManager;
import net.chrupki.ui.view.pages.project.components.Body;
import net.chrupki.ui.view.pages.project.components.Header;

public class ProjectView extends StackPane {

    public ProjectView(PageManager viewManager) {

        Header header = new Header(viewManager);
        Body body = new Body();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(header);
        borderPane.setCenter(body);

        VBox.setVgrow(borderPane, Priority.ALWAYS);

        getChildren().add(borderPane);
    }
}

