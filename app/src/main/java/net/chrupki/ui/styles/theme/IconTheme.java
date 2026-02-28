package net.chrupki.ui.styles.theme;

import net.chrupki.ui.styles.HasCss;

public enum IconTheme implements HasCss {
    PRIMARY("icon-primary"),
    BASE("icon-base"),;

    private final String css;

    IconTheme(String css) {
        this.css = css;
    }

    @Override
    public String css() {
        return css;
    }
}