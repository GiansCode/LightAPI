package io.obadiah.light.nms;

import com.google.common.collect.Lists;
import io.obadiah.light.LightAPI;
import io.obadiah.light.util.Listener;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;
import java.util.stream.Stream;

public abstract class BaseLightHandler implements LightAPI, Listener {

    private static final BlockFace[] SIDES = {
      BlockFace.UP,
      BlockFace.DOWN,
      BlockFace.NORTH,
      BlockFace.EAST,
      BlockFace.SOUTH,
      BlockFace.WEST
    };

    protected BaseLightHandler() {
        this.startListening();
    }

    protected void applyAdjacently(Block block) {
        Block adjacent = this.getAdjacentAirBlock(block);
        this.updateLight(adjacent.getWorld(), adjacent.getLocation().toVector());
    }

    protected abstract void updateChunks(Location location);

    private Block getAdjacentAirBlock(Block block) {
        return Stream.of(SIDES)
          .filter(face -> !(block.getY() == 0x0 && face == BlockFace.DOWN))
          .filter(face -> !(block.getY() == 0xFF && face == BlockFace.UP))
          .map(face -> {
              Block potential = block.getRelative(face);
              return potential.getType().isTransparent() ? potential : block;
          })
          .findFirst()
          .orElse(block);
    }

    private List<Chunk> getUpdatableChunks(Location location) {
        int chunkX = location.getBlockX() >> 4;
        int chunkZ = location.getBlockZ() >> 4;

        List<Chunk> chunks = Lists.newArrayList();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                int cx = chunkX + x;
                int cz = chunkZ + z;

                if (!location.getWorld().getChunkAt(cx, cz).isLoaded()) {
                    continue;
                }

                chunks.add(location.getWorld().getChunkAt(cx, cz));
            }
        }

        return chunks;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        //
    }
}
