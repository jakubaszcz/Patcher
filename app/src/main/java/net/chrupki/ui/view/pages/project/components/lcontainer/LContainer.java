package net.chrupki.ui.view.pages.project.components.lcontainer;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.view.pages.project.components.lcontainer.tag.LCTag;
import net.chrupki.ui.view.pages.project.components.lcontainer.version.LCVersion;

public class LContainer extends VBox {

    public final LCVersion version = new LCVersion();
    public final LCTag tag = new LCTag();

    public LContainer() {

        VBox list = new VBox();
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(list);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        scrollPane.setContent(version);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.getStyleClass().add("invisible-scroll");

        LCHeader header = new LCHeader(view -> {

            switch (view) {
                case "tag" -> {
                    scrollPane.setContent(tag);
                    HubModel.versionModel().clear();
                }
                case "version" -> {
                    scrollPane.setContent(version);
                }
            }
        });

        getStyleClass().add("project-panel");
        getChildren().addAll(
                header,
                scrollPane
        );
    }
}
