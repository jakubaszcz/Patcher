package net.chrupki.ui.view.pages.project.components.lcontainer;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.scroll.ScrollStyle;
import net.chrupki.ui.styles.theme.ContainerTheme;
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
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        scrollPane.setContent(version);

        new ScrollStyle().apply(scrollPane, true);

        LCHeader header = new LCHeader(view -> {

            switch (view) {
                case "tag" -> {
                    scrollPane.setContent(tag);
                    HubModel.versionModel().clear();
                }
                case "version" -> {
                    scrollPane.setContent(version);
                }
                default -> throw new IllegalStateException("Unexpected value: " + view);
            }
        });

        new Styles().apply(this, ContainerTheme.CONTAINER);

        getChildren().addAll(
                header,
                scrollPane
        );
    }
}
