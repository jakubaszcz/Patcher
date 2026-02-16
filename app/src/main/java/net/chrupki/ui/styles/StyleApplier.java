package net.chrupki.ui.styles;

import javafx.scene.Node;

public interface StyleApplier<T> {
    void apply(Node node, T option);
}
