package github.kasuminova.stellarcore.mixin.bloodmagic;

import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import wayoftime.bloodmagic.altar.BloodAltar;
import wayoftime.bloodmagic.common.tile.TileAltar;

@Mixin(BloodAltar.class)
public class MixinBloodAltar {

    @Shadow(remap = false) private TileAltar tileAltar;

    private static void sendUpdatePacketToNearPlayers(final TileAltar altar, final Level world, final BlockPos blockPos) {
        MinecraftServer server = world.getServer();
        if (server == null) {
            return;
        }

        server.execute(() -> {
            if (altar.isRemoved()) {
                return;
            }
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.level != world) {
                    continue;
                }
                double distance = player.blockPosition().distSqr(blockPos);
                if (altar.getUpdatePacket() != null && distance <= 1024) { // 32²
                    player.connection.send(altar.getUpdatePacket());
                }
            }
        });
    }

    @Redirect(method = "startCycle",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;sendBlockUpdated(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;I)V",
                    remap = true),
            remap = false)
    public void onStartCycleNotifyUpdate(final Level world, final BlockPos blockPos, final BlockState pos, final BlockState oldState, final int newState) {
        if (!StellarCore.CONFIG.PERFORMANCE.bloodMagic.bloodAltar) {
            world.sendBlockUpdated(blockPos, pos, oldState, newState);
            return;
        }
        sendUpdatePacketToNearPlayers(tileAltar, world, blockPos);
    }

    @Redirect(method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;sendBlockUpdated(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;I)V",
                    remap = true),
            remap = false)
    public void onUpdateNotifyUpdate(final Level world, final BlockPos blockPos, final BlockState pos, final BlockState oldState, final int newState) {
        if (!StellarCore.CONFIG.PERFORMANCE.bloodMagic.bloodAltar) {
            world.sendBlockUpdated(blockPos, pos, oldState, newState);
            return;
        }
        sendUpdatePacketToNearPlayers(tileAltar, world, blockPos);
    }

    @Redirect(method = "updateAltar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;sendBlockUpdated(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;I)V",
                    remap = true),
            remap = false)
    public void onUpdateAltarCycleNotifyUpdate(final Level world, final BlockPos blockPos, final BlockState pos, final BlockState oldState, final int newState) {
        if (!StellarCore.CONFIG.PERFORMANCE.bloodMagic.bloodAltar) {
            world.sendBlockUpdated(blockPos, pos, oldState, newState);
            return;
        }
        sendUpdatePacketToNearPlayers(tileAltar, world, blockPos);
    }

}