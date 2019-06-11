package io.obadiah.light.nms.v1_10;

import io.obadiah.light.nms.BaseLightHandler;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.EnumSkyBlock;
import net.minecraft.server.v1_10_R1.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;

public class LightHandler extends BaseLightHandler {

    @Override
    public void setLight(World world, int x, int y, int z, int lightLevel) {
        WorldServer server = ((CraftWorld) world).getHandle();
        BlockPosition position = new BlockPosition(x, y, z);
        server.a(EnumSkyBlock.BLOCK, position, lightLevel);
        this.applyAdjacently(world.getBlockAt(x, y, z));
    }

    @Override
    public void updateLight(World world, int x, int y, int z) {
        WorldServer server = ((CraftWorld) world).getHandle();
        BlockPosition position = new BlockPosition(x, y, z);
        server.c(EnumSkyBlock.BLOCK, position);
    }
}
