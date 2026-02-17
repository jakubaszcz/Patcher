package net.chrupki.ui.view.pages.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ContainerTheme;
import net.chrupki.ui.styles.theme.TextTheme;

public class PatchHeader extends HBox {

    public PatchHeader() {
        Label title = new Label();
        title.textProperty().bind(HubModel.versionModel().getName());
        new Styles().apply(title, TextTheme.TITLE);


        Button addButton = new Button("+");
        new Styles().apply(addButton, ButtonTheme.NORMAL);

        Button exportButton = new Button("Export");
        new Styles().apply(exportButton, ButtonTheme.NORMAL);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 12, 10, 12));
        setSpacing(8);

        addButton.setOnAction(e -> {
            GlobalModel.setSwitchProjectsModal(true);
            GlobalModel.setSwitchCreatePatchProjectModal(true);
        });

        exportButton.setOnAction(e -> {
            GlobalModel.setSwitchProjectsModal(true);
            GlobalModel.setSwitchExportModal(true);
        });

        setMaxWidth(Double.MAX_VALUE);

        new Styles().apply(this, ContainerTheme.HEADER);

        getChildren().addAll(
                title,
                spacer,
                addButton,
                exportButton
                );
    }

}
