package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.view.manager.PageManager;
import net.chrupki.ui.view.pages.projects.ProjectsView;

public class Header extends HBox {

    public Header(PageManager viewManager) {

        Button backButton = new Button("←");
        backButton.getStyleClass().add("header-back-button");

        Label backLabel = new Label("Go back");
        backLabel.getStyleClass().add("header-back-text");

        HBox backGroup = new HBox(8, backButton, backLabel);
        backGroup.setAlignment(Pos.CENTER_LEFT);

        setPadding(new Insets(12, 16, 12, 16));
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(12);

        getStyleClass().add("header");
        getChildren().add(backGroup);

        backButton.setOnAction(e -> {
            viewManager.show(new ProjectsView(viewManager));
            HubModel.versionModel().clear();
        });
    }
}
