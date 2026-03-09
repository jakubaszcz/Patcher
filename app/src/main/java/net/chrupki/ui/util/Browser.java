package net.chrupki.ui.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Browser {

    private Browser() {}

    public static void open(File file) {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win") || os.contains("mac")) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                    Desktop.getDesktop().open(file);
                    return;
                }
            }

            if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                new ProcessBuilder("xdg-open", file.getAbsolutePath()).start();
                return;
            }

            // Fallback for any other OS or if specialized methods didn't work
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Desktop not supported for opening file: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
            // Last resort for Linux if Desktop failed and we haven't tried xdg-open yet
            if (!(os.contains("nix") || os.contains("nux") || os.contains("aix"))) {
                try {
                     new ProcessBuilder("xdg-open", file.getAbsolutePath()).start();
                } catch (IOException ex) {
                    System.err.println("Error opening with xdg-open: " + ex.getMessage());
                }
            }
        }
    }
}
