package github.kasuminova.stellarcore.mixin.botania;

import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.crafting.recipe.CosmeticAttachRecipe;

@SuppressWarnings("MethodMayBeStatic")
@Mixin(value = CosmeticAttachRecipe.class, remap = false)
public class MixinCosmeticAttachRecipe {

    @Inject(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z", at = @At("HEAD"), cancellable = true)
    private void removeMatches(final CraftingContainer inv, final Level world, final CallbackInfoReturnable<Boolean> cir) {
        if (!StellarCore.CONFIG.FEATURES.botania.disableCosmeticRecipe) {
            return;
        }
        cir.setReturnValue(false);
    }

    @Inject(method = "assemble(Lnet/minecraft/world/inventory/CraftingContainer;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void removeGetCraftingResult(final CraftingContainer inv, final CallbackInfoReturnable<ItemStack> cir) {
        if (!StellarCore.CONFIG.FEATURES.botania.disableCosmeticRecipe) {
            return;
        }
        cir.setReturnValue(ItemStack.EMPTY);
    }

}