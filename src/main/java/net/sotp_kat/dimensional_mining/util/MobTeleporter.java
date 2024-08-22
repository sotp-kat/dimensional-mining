package net.sotp_kat.dimensional_mining.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.ITeleporter;
import net.sotp_kat.dimensional_mining.block.PortalBlock;
import net.sotp_kat.dimensional_mining.registries.BlockRegistry;

import java.util.function.Function;


public class MobTeleporter implements ITeleporter {
    private static final BlockPos SPAWN_STRUCTURE = new BlockPos(-2,128,-2);

    public BlockPos thisPos;

    public MobTeleporter(BlockPos pos) {
        this.thisPos = pos;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        if (!(entity instanceof ServerPlayer player)) return entity;

        if (destWorld.equals(destWorld.getServer().getLevel(PortalBlock.miningDimLevel))) {
            for (BlockPos placePos : BlockPos.betweenClosed(SPAWN_STRUCTURE,SPAWN_STRUCTURE.south(4).east(4))) {
                destWorld.setBlock(placePos, Blocks.OBSIDIAN.defaultBlockState(),3);
            }
            BlockPos centeredSpawn = SPAWN_STRUCTURE.south(2).east(2).above();
            destWorld.setBlock(centeredSpawn, BlockRegistry.PORTAL_BLOCK.get().defaultBlockState(),3);
            player.teleportTo(destWorld, centeredSpawn.getX()+0.5, centeredSpawn.getY()+1, centeredSpawn.getZ()+0.5, 0,0);
        }
        else {
            BlockPos spawn = player.getRespawnPosition();
            if (spawn == null) spawn = destWorld.getSharedSpawnPos();
            player.teleportTo(destWorld,spawn.getX()+0.5,spawn.getY(),spawn.getZ()+0.5,0,0);
        }

        return player;
    }
}
