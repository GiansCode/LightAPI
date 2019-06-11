package io.obadiah.light;

import io.obadiah.light.util.SpigotVersion;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import javax.annotation.concurrent.Immutable;

@Immutable
public interface LightAPI {

    /**
     * Obtains a LightAPI instance depending on your Minecraft server version.
     *
     * @return The corresponding LightAPI instance, as determined by your Minecraft server version.
     *
     * @throws IllegalStateException If the server instance is not supported by LightAPI.
     */
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

        throw new IllegalStateException(SpigotVersion.BUKKIT_VERSION + " is unsupported by LightAPI.");
    }

    /**
     * Sets the light level of a specific block.
     *
     * @param world World enclosing the block.
     * @param x X-axis of the block.
     * @param y Y-axis of the block.
     * @param z Z-axis of the block.
     * @param lightLevel The light level to set the block to.
     */
    void setLight(World world, int x, int y, int z, int lightLevel);

    /**
     * Sets the light level of a specific block.
     *
     * @param world World enclosing the block.
     * @param vector Vector of the block.
     * @param lightLevel The light level to set the block to.
     */
    default void setLight(World world, Vector vector, int lightLevel) {
        this.setLight(world, vector.getBlockX(), vector.getBlockY(), vector.getBlockZ(), lightLevel);
    }

    /**
     * Sets the light level of a specific block.
     *
     * @param location Location of the block.
     * @param lightLevel The light level to set the block to.
     */
    default void setLight(Location location, int lightLevel) {
        this.setLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), lightLevel);
    }

    /**
     * Removes all light from a specific block.
     *
     * @param world World enclosing the block.
     * @param x X-axis of the block.
     * @param y Y-axis of the block.
     * @param z Z-axis of the block.
     */
    default void removeLight(World world, int x, int y, int z) {
        this.updateLight(world, x, y, z);
    }

    /**
     * Removes all light from a specific block.
     *
     * @param world World enclosing the block.
     * @param vector Vector of the block.
     */
    default void removeLight(World world, Vector vector) {
        this.removeLight(world, vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
    }

    /**
     * Removes all light from a specific block.
     *
     * @param location Location of the block.
     */
    default void removeLight(Location location) {
        this.removeLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * Forces a block light update on the specific block
     *
     * @param world World enclosing the block.
     * @param x X-axis of the block.
     * @param y Y-axis of the block.
     * @param z Z-axis of the block.
     */
    void updateLight(World world, int x, int y, int z);

    /**
     * Forces a block light update on the specific block
     *
     * @param world World enclosing the block.
     * @param vector Vector of the block.
     */
    default void updateLight(World world, Vector vector) {
        this.updateLight(world, vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
    }

    /**
     * Forces a block light update on the specific block
     *
     * @param location Location of the block.
     */
    default void updateLight(Location location) {
        this.updateLight(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
