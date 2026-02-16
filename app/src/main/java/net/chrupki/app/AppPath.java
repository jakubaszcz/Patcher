package net.chrupki.app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppPath {

    private static String APP_NAME = "patcher";
    private static String TEMPLATES = "templates";

    private AppPath() {
    }

    private enum OS {
        Linux,
        MacOS,
        Windows
    }

    public static Path getDataDir() {
        Path base = getBaseDataDir();
        Path appDir = base.resolve(APP_NAME);
        Path templateDir = appDir.resolve(TEMPLATES);
        createIfMissing(appDir);
        createIfMissing(templateDir);
        return appDir;
    }

    public static Path getConfigDir() {
        Path base = getBaseConfigDir();
        Path appDir = base.resolve(APP_NAME);
        createIfMissing(appDir);
        return appDir;
    }

    public static Path getCacheDir() {
        Path base = getBaseCacheDir();
        Path appDir = base.resolve(APP_NAME);
        createIfMissing(appDir);
        return appDir;
    }

    private static OS getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return OS.Windows;
        if (os.contains("lin")) return OS.Linux;
        if (os.contains("mac")) return OS.MacOS;
        return null;
    }

    private static Path getBaseDataDir() {
        OS os = getOS();

        return switch (os) {
            case Windows -> Paths.get(System.getenv("APPDATA"));
            case MacOS -> Paths.get(
                    System.getProperty("user.home"),
                    "Library",
                    "Application Support"
            );
            case Linux -> {
                String xdg = System.getenv("XDG_DATA_HOME");
                if (xdg != null && !xdg.isBlank()) {
                    yield Paths.get(xdg);
                }
                yield Paths.get(
                        System.getProperty("user.home"),
                        ".local",
                        "share"
                );
            }
        };
    }

    private static Path getBaseConfigDir() {
        OS os = getOS();

        return switch (os) {
            case Windows -> Paths.get(System.getenv("APPDATA"));
            case MacOS -> Paths.get(
                    System.getProperty("user.home"),
                    "Library",
                    "Application Support"
            );
            case Linux -> {
                String xdg = System.getenv("XDG_DATA_HOME");
                if (xdg != null && !xdg.isBlank()) {
                    yield Paths.get(xdg);
                }
                yield Paths.get(
                        System.getProperty("user.home"),
                        ".local",
                        "share"
                );
            }
        };
    }

    private static Path getBaseCacheDir() {
        OS os = getOS();

        return switch (os) {
            case Windows -> Paths.get(System.getenv("APPDATA"));
            case MacOS -> Paths.get(
                    System.getProperty("user.home"),
                    "Library",
                    "Application Support"
            );
            case Linux -> {
                String xdg = System.getenv("XDG_DATA_HOME");
                if (xdg != null && !xdg.isBlank()) {
                    yield Paths.get(xdg);
                }
                yield Paths.get(
                        System.getProperty("user.home"),
                        ".local",
                        "share"
                );
            }
        };
    }

    private static void createIfMissing(Path path) {
        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            throw new RuntimeException("Unable to locate path directory " + path, e);
        }
    }
}
