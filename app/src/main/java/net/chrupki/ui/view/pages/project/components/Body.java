package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.view.pages.project.components.lcontainer.LContainer;

public class Body extends HBox {

    public Body() {

        LContainer lcontainer = new LContainer();
        Patch patches = new Patch();

        setSpacing(6);
        setPadding(new Insets(6));

        setFillHeight(true);

        lcontainer.prefWidthProperty().bind(
                widthProperty().multiply(0.35)
        );

        patches.visibleProperty().bind(HubModel.versionModel().getId().isNotNull());

        patches.prefWidthProperty().bind(
                widthProperty().multiply(0.65)
        );

        VBox.setVgrow(lcontainer, Priority.ALWAYS);
        VBox.setVgrow(patches, Priority.ALWAYS);

        getChildren().addAll(lcontainer, patches);
    }
}
