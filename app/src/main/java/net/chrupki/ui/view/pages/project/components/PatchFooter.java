package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.HubModel;
import net.chrupki.request.ExportRequest;
import net.chrupki.ui.model.GlobalModel;

import java.io.File;
import java.util.List;

public class PatchFooter extends HBox {

    public PatchFooter() {
        Label tip = new Label("To be able to export you need to lock the version.");
        tip.getStyleClass().add("project-item-meta");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button releaseButton = new Button("Release");
        releaseButton.getStyleClass().add("project-add-button");

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        setMaxWidth(Double.MAX_VALUE);

        getStyleClass().add("project-header");

        getChildren().addAll(
                tip,
                spacer,
                releaseButton
        );
    }
}
