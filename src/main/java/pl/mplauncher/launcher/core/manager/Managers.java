package pl.mplauncher.launcher.core.manager;

public class Managers {

    private static ModpackManager modpackManager;

    public static void initialize() {
        modpackManager = new ModpackManager();
    }

    public static ModpackManager getModpackManager() {
        return modpackManager;
    }
}
