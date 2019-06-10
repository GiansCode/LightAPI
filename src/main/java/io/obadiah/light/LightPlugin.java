package io.obadiah.light;

import io.obadiah.light.util.SpigotVersion;
import org.bukkit.plugin.java.JavaPlugin;

public class LightPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        if (SpigotVersion.getServerVersion() == SpigotVersion.UNSUPPORTED) {
            throw new IllegalStateException(SpigotVersion.BUKKIT_VERSION + " is unsupported by LightPlugin.");
        }
    }
}
