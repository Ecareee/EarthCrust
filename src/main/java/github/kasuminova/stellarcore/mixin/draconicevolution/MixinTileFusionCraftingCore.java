package github.kasuminova.stellarcore.mixin.draconicevolution;

import com.brandon3055.draconicevolution.api.crafting.IFusionRecipe;
import com.brandon3055.draconicevolution.blocks.tileentity.TileFusionCraftingCore;
import github.kasuminova.stellarcore.StellarCore;
import github.kasuminova.stellarcore.mixinutil.IFusionCraftingCore;
import github.kasuminova.stellarcore.mixinutil.ITileCraftingInjector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(value = TileFusionCraftingCore.class, remap = false)
public abstract class MixinTileFusionCraftingCore implements IFusionCraftingCore {

    @Shadow public abstract void cancelCraft();

    @Shadow @Nullable public abstract IFusionRecipe getActiveRecipe();


    @Redirect(
            method = "updateInjectors",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    remap = false
            ),
            remap = false
    )
    @SuppressWarnings({"unchecked", "rawtypes"})
    private boolean redirectAddInjector(final List instance, final Object e) {
        if (!StellarCore.CONFIG.BUG_FIXES.draconicEvolution.craftingInjector) {
            return instance.add(e);
        }
        if (e instanceof ITileCraftingInjector injector) {
            injector.onInjectorAddToCore(this);
        }
        return instance.add(e);
    }

    @Override
    @SuppressWarnings("AddedMixinMembersNamePattern")
    public void onInjectorUnload() {
        if (this.getActiveRecipe() != null) {
            cancelCraft();
        }
    }

}