package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;

public class Body extends HBox {

    public Body() {

        Version versions = new Version();
        Patch patches = new Patch();

        setSpacing(6);
        setPadding(new Insets(6));

        setFillHeight(true);

        versions.prefWidthProperty().bind(
                widthProperty().multiply(0.35)
        );

        patches.visibleProperty().bind(HubModel.versionModel().getId().isNotNull());

        patches.prefWidthProperty().bind(
                widthProperty().multiply(0.65)
        );

        VBox.setVgrow(versions, Priority.ALWAYS);
        VBox.setVgrow(patches, Priority.ALWAYS);

        getChildren().addAll(versions, patches);
    }
}
