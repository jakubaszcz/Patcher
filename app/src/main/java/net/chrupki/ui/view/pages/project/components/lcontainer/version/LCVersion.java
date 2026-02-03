package net.chrupki.ui.view.pages.project.components.lcontainer.version;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import net.chrupki.dto.VersionDTO;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.view.pages.project.components.lcontainer.version.components.LCVersionContainer;

public class LCVersion extends VBox {

    public LCVersion() {
        ObservableList<VersionDTO> versions = GlobalModel.getVersions();

        refresh(versions);

        versions.addListener((ListChangeListener<VersionDTO>) c ->
                refresh(versions)
        );
    }

    private void refresh(ObservableList<VersionDTO> versions) {
        getChildren().clear();

        for (VersionDTO v : versions) {
            getChildren().add(
                    new LCVersionContainer(
                            v,
                            HubController.getVersionController()::selectVersion
                    )
            );
        }
    }

}
