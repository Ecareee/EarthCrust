package github.kasuminova.stellarcore.mixin.immersiveengineering;

import blusunrize.immersiveengineering.common.blocks.IEEntityBlock;
import blusunrize.immersiveengineering.common.util.inventory.IIEInventory;
import com.llamalad7.mixinextras.sugar.Local;
import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.Iterator;

@Mixin(IEEntityBlock.class)
@SuppressWarnings("MethodMayBeStatic")
public class MixinIEEntityBlock {

    @Redirect(
            method = "playerDestroy",
            at = @At(value = "INVOKE", target = "Ljava/util/Iterator;hasNext()Z", remap = false),
            remap = false
    )
    @SuppressWarnings("rawtypes")
    private boolean onGetDrops(final Iterator instance, @Local(name = "tile") BlockEntity tile) {
        if (instance.hasNext()) {
            return true;
        }
        if (!StellarCore.CONFIG.BUG_FIXES.immersiveEngineering.blockIEMultiblock) {
            return false;
        }
        if (tile instanceof IIEInventory ieInv) {
            NonNullList<ItemStack> inventory = ieInv.getInventory();
            if (inventory == null || inventory.isEmpty()) {
                return false;
            }
            Collections.fill(inventory, ItemStack.EMPTY);
        }
        return false;
    }

}