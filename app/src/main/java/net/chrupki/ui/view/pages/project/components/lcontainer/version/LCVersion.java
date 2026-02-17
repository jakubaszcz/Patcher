package net.chrupki.ui.view.pages.project.components.lcontainer.version;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.dto.VersionDTO;
import net.chrupki.ui.EmptyStateView;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.view.pages.project.components.lcontainer.version.components.LCVersionContainer;

public class LCVersion extends VBox {

    public LCVersion() {
        ObservableList<VersionDTO> versions = GlobalModel.getVersions();

        setFillWidth(true);

        refresh(versions);

        versions.addListener((ListChangeListener<VersionDTO>) c ->
                refresh(versions)
        );
    }

    private void refresh(ObservableList<VersionDTO> versions) {
        getChildren().clear();

        if (versions.isEmpty()) {
            EmptyStateView emptyView = new EmptyStateView(
                    "Version",
                    "Click here to create your first version",
                    () -> {
                        GlobalModel.setSwitchProjectsModal(true);
                        GlobalModel.setSwitchCreateVersionProjectModal(true);
                    }
            );
            VBox.setVgrow(emptyView, Priority.ALWAYS);
            getChildren().add(emptyView);
            return;
        }


        for (int i = 0; i < versions.size(); i++) {
            getChildren().add(
                    new LCVersionContainer(
                            versions.get(i),
                            HubController.getVersionController()::selectVersion,
                            i % 2 == 0 ? true : false
                    )
            );
        }
    }

}
