package github.kasuminova.stellarcore.mixin.cfm;

import com.llamalad7.mixinextras.sugar.Local;
import com.mrcrayfish.furniture.block.FurnitureBlock;
import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.IntStream;

@Mixin(FurnitureBlock.class)
@SuppressWarnings("MethodMayBeStatic")
public class MixinFurnitureBlock {

    @Inject(method = "onRemove",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/Containers;dropContents(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/Container;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void injectDropContentsVanilla(final BlockState state, final Level world, final BlockPos pos, BlockState newState, boolean isMoving, final CallbackInfo ci, @Local(name = "container") Container container) {
        if (!StellarCore.CONFIG.BUG_FIXES.mrCrayfishFurniture.blockFurnitureTile) {
            return;
        }
        IntStream.range(0, container.getContainerSize())
                .filter(i -> !container.getItem(i).isEmpty())
                .forEach(i -> container.setItem(i, ItemStack.EMPTY));
    }

}