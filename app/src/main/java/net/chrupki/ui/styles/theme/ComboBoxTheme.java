package net.chrupki.ui.styles.theme;

import net.chrupki.ui.styles.HasCss;

public enum ComboBoxTheme implements HasCss {
    NORMAL("combo-box");

    private final String css;

    ComboBoxTheme(String css) {
        this.css = css;
    }

    @Override
    public String css() {
        return css;
    }
}