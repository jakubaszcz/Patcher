package net.chrupki.ui.styles.theme;

import net.chrupki.ui.styles.HasCss;

public enum ContainerTheme implements HasCss {
    CONTAINER("container"),
    HEADER("container-header"),
    BODY("container-body"),
    BODY_PAIR("container-body-pair");

    private final String css;

    ContainerTheme(String css) {
        this.css = css;
    }

    @Override
    public String css() {
        return css;
    }
}