package net.chrupki.ui.styles.theme;

import net.chrupki.ui.styles.HasCss;

public enum TextFieldTheme implements HasCss {
    NORMAL("text-field");

    private final String css;

    TextFieldTheme(String css) {
        this.css = css;
    }

    @Override
    public String css() {
        return css;
    }
}