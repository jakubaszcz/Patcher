package net.chrupki.ui.util;

import net.chrupki.Patcher;

import java.util.Objects;

public class Css {

    private Css() {}

    public static String load(String name) {
        return Objects.requireNonNull(
                Patcher.class.getResource("/styles/" + name),
                "Css not found : " + name).toExternalForm();
    }

}
