package net.chrupki.ui.styles.scroll;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import net.chrupki.ui.styles.StyleApplier;

public class ScrollStyle implements StyleApplier<Boolean> {

    @Override
    public void apply(Node node, Boolean option) {
        String visible = "scroll-visible";
        String invisible = "scroll-invisible";

        node.getStyleClass().removeAll(
                visible,
                invisible
        );

        if (node instanceof ScrollPane scrollPane) {
            if (option) {
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            } else {
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        }

        if (option) node.getStyleClass().add(visible);
        else node.getStyleClass().add(invisible);
    }

}
