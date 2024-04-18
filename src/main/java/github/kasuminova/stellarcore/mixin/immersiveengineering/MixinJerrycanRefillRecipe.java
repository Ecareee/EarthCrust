package github.kasuminova.stellarcore.mixin.immersiveengineering;

import blusunrize.immersiveengineering.common.crafting.JerrycanRefillRecipe;
import blusunrize.immersiveengineering.common.register.IEItems;
import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(JerrycanRefillRecipe.class)
@SuppressWarnings("MethodMayBeStatic")
public class MixinJerrycanRefillRecipe {

    @Redirect(
            method = "getComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fluids/FluidUtil;getFluidHandler(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraftforge/common/util/LazyOptional;",
                    remap = false
            ),
            remap = false)
    private LazyOptional<IFluidHandlerItem> redirectGetComponentsGetFluidHandler(final ItemStack itemStack) {
        if (!StellarCore.CONFIG.BUG_FIXES.immersiveEngineering.fixJerryCanRecipe) {
            return FluidUtil.getFluidHandler(itemStack);
        }
        if (IEItems.Misc.JERRYCAN.asItem().equals(itemStack.getItem())) {
            return LazyOptional.empty();
        }
        return FluidUtil.getFluidHandler(itemStack);
    }

    @Redirect(
            method = "getComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fluids/FluidUtil;getFluidContained(Lnet/minecraft/world/item/ItemStack;)Ljava/util/Optional;",
                    remap = false
            ),
            remap = false)
    private Optional<FluidStack> redirectGetComponentsGetFluidContained(final ItemStack itemStack) {
        if (!StellarCore.CONFIG.BUG_FIXES.immersiveEngineering.fixJerryCanRecipe) {
            return FluidUtil.getFluidContained(itemStack);
        }
        if (IEItems.Misc.JERRYCAN.asItem().equals(itemStack.getItem())) {
            return Optional.empty();
        }
        return FluidUtil.getFluidContained(itemStack);
    }

}