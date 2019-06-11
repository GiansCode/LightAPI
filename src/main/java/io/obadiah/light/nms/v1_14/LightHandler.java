package io.obadiah.light.nms.v1_14;

import io.obadiah.light.nms.BaseLightHandler;
import net.minecraft.server.v1_14_R1.EnumSkyBlock;
import net.minecraft.server.v1_14_R1.NibbleArray;
import net.minecraft.server.v1_14_R1.SectionPosition;
import net.minecraft.server.v1_14_R1.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;

public class LightHandler extends BaseLightHandler {

    @Override
    public void setLight(World world, int x, int y, int z, int lightLevel) {
        WorldServer server = ((CraftWorld) world).getHandle();
        SectionPosition position = SectionPosition.a(x, y, z);
        server.getChunkProvider().getLightEngine().a(EnumSkyBlock.BLOCK, position, new NibbleArray(new byte[15]));
        this.applyAdjacently(world.getBlockAt(x, y, z));
    }

    @Override
    public void updateLight(World world, int x, int y, int z) {
        WorldServer server = ((CraftWorld) world).getHandle();
        SectionPosition position = SectionPosition.a(x, y, z);
        server.getChunkProvider().getLightEngine().a(EnumSkyBlock.BLOCK, position, null);
    }
}
