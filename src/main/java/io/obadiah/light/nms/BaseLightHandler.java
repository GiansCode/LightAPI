package io.obadiah.light.nms;

import io.obadiah.light.LightAPI;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.stream.Stream;

public abstract class BaseLightHandler implements LightAPI {

    private static final BlockFace[] SIDES = {
      BlockFace.UP,
      BlockFace.DOWN,
      BlockFace.NORTH,
      BlockFace.EAST,
      BlockFace.SOUTH,
      BlockFace.WEST
    };

    protected BaseLightHandler() {}

    protected void applyAdjacently(Block block) {
        Block adjacent = this.getAdjacentAirBlock(block);
        this.updateLight(adjacent.getWorld(), adjacent.getLocation().toVector());
    }

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
}
