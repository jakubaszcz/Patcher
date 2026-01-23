package net.chrupki.ui.view.pages.project.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.view.pages.project.dto.VersionDTO;

public class Version extends VBox {

    private final VBox list = new VBox();
    private final ScrollPane scrollPane = new ScrollPane();

    public Version() {

        ObservableList<VersionDTO> versions = ProjectModel.getVersions();

        scrollPane.setContent(list);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.getStyleClass().add("invisible-scroll");

        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        refresh(versions);

        versions.addListener((ListChangeListener<VersionDTO>) c ->
                refresh(versions)
        );

        getStyleClass().add("project-panel");
        getChildren().addAll(
                new VersionHeader(),
                scrollPane
        );
    }

    private void refresh(ObservableList<VersionDTO> versions) {
        list.getChildren().clear();

        for (VersionDTO v : versions) {
            list.getChildren().add(
                    new VersionContainer(
                            v,
                            HubController.getVersionController()::selectVersion
                    )
            );
        }
    }
}
