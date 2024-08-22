package net.sotp_kat.dimensional_mining.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.sotp_kat.dimensional_mining.DimensionalMining;
import net.sotp_kat.dimensional_mining.util.MobTeleporter;

public class PortalBlock extends Block {

    public static final ResourceKey<Level> miningDimLevel = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DimensionalMining.MODID, "mining_dim"));

    public PortalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            handlePortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }

    private void handlePortal(Player player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverLevel) {
            MinecraftServer minecraftServer = serverLevel.getServer();
            ResourceKey<Level> resourceKey = (player.level().dimension() == miningDimLevel) ? Level.OVERWORLD : miningDimLevel;

            ServerLevel portalDimension = minecraftServer.getLevel(resourceKey);
            if (portalDimension != null && !player.isPassenger()) {
                player.changeDimension(portalDimension, new MobTeleporter(pPos));
            }
        }
    }
}
