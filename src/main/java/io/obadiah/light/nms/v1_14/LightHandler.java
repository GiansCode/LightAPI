package io.obadiah.light.nms.v1_14;

import io.obadiah.light.nms.BaseLightHandler;
import net.minecraft.server.v1_14_R1.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;

public class LightHandler extends BaseLightHandler {

    @Override
    public void setLight(World world, int x, int y, int z, int lightLevel) {
        WorldServer server = ((CraftWorld) world).getHandle();
        BlockPosition position = new BlockPosition(x, y, z);
        LightEngineBlock block = (LightEngineBlock) server.getChunkProvider().getLightEngine().a(EnumSkyBlock.BLOCK);

        block.a(position, lightLevel);
        this.applyAdjacently(world.getBlockAt(x, y, z));
    }

    @Override
    public void updateLight(World world, int x, int y, int z) {
        WorldServer server = ((CraftWorld) world).getHandle();
        BlockPosition position = new BlockPosition(x, y, z);
        server.getChunkProvider().getLightEngine().a(position);
    }

    @Override
    protected void updateChunks(Location location) {

    }
}
