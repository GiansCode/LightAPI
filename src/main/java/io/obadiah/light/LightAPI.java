package io.obadiah.light;

import io.obadiah.light.util.SpigotVersion;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import javax.annotation.concurrent.Immutable;

@Immutable
public interface LightAPI {

    static LightAPI get() {
        switch (SpigotVersion.getServerVersion()) {
            case v1_8:
                return new io.obadiah.light.nms.v1_8.LightHandler();
            case v1_9:
                return new io.obadiah.light.nms.v1_9.LightHandler();
            case v1_10:
                return new io.obadiah.light.nms.v1_10.LightHandler();
            case v1_11:
                return new io.obadiah.light.nms.v1_11.LightHandler();
            case v1_12:
                return new io.obadiah.light.nms.v1_12.LightHandler();
            case v1_13:
                return new io.obadiah.light.nms.v1_13.LightHandler();
            case v1_14:
                return new io.obadiah.light.nms.v1_14.LightHandler();
        }

        throw new IllegalStateException(SpigotVersion.BUKKIT_VERSION + " is unsupported by LightPlugin.");
    }

    void createLight(World world, int x, int y, int z, int lightLevel);

    default void createLight(World world, Vector vector, int lightLevel) {
        this.createLight(world, vector.getBlockX(), vector.getBlockY(), vector.getBlockZ(), lightLevel);
    }

    default void createLight(Location location, int lightLevel) {
        this.createLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), lightLevel);
    }

    void deleteLight(World world, int x, int y, int z);

    default void deleteLight(World world, Vector vector) {
        this.deleteLight(world, vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
    }

    default void deleteLight(Location location) {
        this.deleteLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    void updateLight(World world, int x, int y, int z);

    default void updateLight(World world, Vector vector) {
        this.updateLight(world, vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
    }

    default void updateLight(Location location) {
        this.updateLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
