package net.chrupki.ui.styles.theme;

import net.chrupki.ui.styles.HasCss;

public enum ButtonTheme implements HasCss {
    NORMAL("button-normal"),
    EDIT("button-edit"),
    DANGER("button-danger"),
    CANCEL("button-cancel");

    private final String css;

    ButtonTheme(String css) {
        this.css = css;
    }

    @Override
    public String css() {
        return css;
    }
}