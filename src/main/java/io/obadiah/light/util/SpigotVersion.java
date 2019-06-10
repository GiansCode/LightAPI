package io.obadiah.light.util;

import org.bukkit.Bukkit;

import javax.annotation.concurrent.Immutable;
import java.util.stream.Stream;

@Immutable
public enum SpigotVersion {

    v1_8,
    v1_9,
    v1_10,
    v1_11,
    v1_12,
    v1_13,
    v1_14,

    UNSUPPORTED;

    public static final String BUKKIT_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    public static SpigotVersion getServerVersion() {
        return getByVersion(BUKKIT_VERSION);
    }

    public static SpigotVersion getByVersion(String version) {
        return Stream.of(SpigotVersion.values())
          .filter(ver -> version.contains(ver.name()))
          .findFirst()
          .orElse(SpigotVersion.UNSUPPORTED);
    }
}
