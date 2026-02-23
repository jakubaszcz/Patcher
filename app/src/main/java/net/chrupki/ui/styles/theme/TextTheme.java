package net.chrupki.ui.styles.theme;

import net.chrupki.ui.styles.HasCss;

public enum TextTheme implements HasCss {
    TITLE("title"),
    SUBTITLE("subtitle"),
    TEXT_MAIN("text-main"),
    TEXT_MUTED("text-muted"),
    TEXT_LINK("text-link"),
    TEXT_BADGE("text-badge"),
    TEXT_ERROR_BADGE("text-error-badge"),
    TEXT_ITEM("text-item");

    private final String css;

    TextTheme(String css) {
        this.css = css;
    }

    @Override
    public String css() {
        return css;
    }
}