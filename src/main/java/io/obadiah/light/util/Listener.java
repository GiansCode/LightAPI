package io.obadiah.light.util;

import io.obadiah.light.LightPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public interface Listener extends org.bukkit.event.Listener {

    default void startListening() {
        Bukkit.getPluginManager().registerEvents(this, JavaPlugin.getPlugin(LightPlugin.class));
    }

    default void stopListening() {
        HandlerList.unregisterAll(this);
    }
}
