package net.chrupki.ui.styles;

import javafx.scene.Node;

public class Styles implements StyleApplier<HasCss> {

    @Override
    public void apply(Node node, HasCss option) {
        System.out.println(option.css());
        node.getStyleClass().add(option.css());
    }

}
