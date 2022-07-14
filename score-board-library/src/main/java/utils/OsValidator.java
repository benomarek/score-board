package utils;

public class OsValidator {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    private OsValidator() {
    }

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        return (OS.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix")
                || OS.contains("nux")
                || OS.indexOf("aix") > 0);

    }
}
