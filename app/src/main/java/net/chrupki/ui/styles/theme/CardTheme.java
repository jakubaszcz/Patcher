package net.chrupki.ui.styles.theme;

import net.chrupki.ui.styles.HasCss;

public enum CardTheme implements HasCss {
    NORMAL("card");

    private final String css;

    CardTheme(String css) {
        this.css = css;
    }

    @Override
    public String css() {
        return css;
    }
}