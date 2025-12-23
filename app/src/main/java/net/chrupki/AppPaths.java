package net.chrupki;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AppPaths {

    private static String APP_NAME = "patcher";

    private AppPaths() {
    }

    private enum OS {
        Linux,
        MacOS,
        Windows
    }

    public static Path GetDataDir() {
        Path base = GetBaseDataDir();
        Path appDir = base.resolve(APP_NAME);
        CreateIfMissing(appDir);
        return appDir;
    }

    public static Path GetConfigDir() {
        Path base = GetBaseConfigDir();
        Path appDir = base.resolve(APP_NAME);
        CreateIfMissing(appDir);
        return appDir;
    }

    public static Path GetCacheDir() {
        Path base = GetBaseCacheDir();
        Path appDir = base.resolve(APP_NAME);
        CreateIfMissing(appDir);
        return appDir;
    }

    private static OS GetOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return OS.Windows;
        if (os.contains("lin")) return OS.Linux;
        if (os.contains("mac")) return OS.MacOS;
        return null;
    }

    private static Path GetBaseDataDir() {
        OS os = GetOS();

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

    private static Path GetBaseConfigDir() {
        OS os = GetOS();

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

    private static Path GetBaseCacheDir() {
        OS os = GetOS();

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

    private static void CreateIfMissing(Path path) {
        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            throw new RuntimeException("Unable to locate path directory " + path, e);
        }
    }
}
