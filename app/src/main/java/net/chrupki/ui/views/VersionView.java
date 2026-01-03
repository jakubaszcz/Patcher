package net.chrupki.ui.views;

import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.project.services.VersionService;
import net.chrupki.ui.components.CreatePatchForm;
import net.chrupki.ui.components.VersionContainer;
import net.chrupki.ui.controllers.VersionController;


public class VersionView extends VBox {

    private static VBox view;


    public VersionView() {

        view = new VBox(10);

        view.visibleProperty().bind(AppData.getVersionSelected());

        view.getChildren().add(
                new CreatePatchForm()
        );

        this.getChildren().add(view);
    }
}
