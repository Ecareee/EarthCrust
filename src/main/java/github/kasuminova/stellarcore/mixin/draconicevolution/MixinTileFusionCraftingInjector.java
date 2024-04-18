package github.kasuminova.stellarcore.mixin.draconicevolution;

import com.brandon3055.draconicevolution.blocks.tileentity.TileFusionCraftingInjector;
import github.kasuminova.stellarcore.StellarCore;
import github.kasuminova.stellarcore.mixinutil.IFusionCraftingCore;
import github.kasuminova.stellarcore.mixinutil.ITileCraftingInjector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TileFusionCraftingInjector.class)
public class MixinTileFusionCraftingInjector extends BlockEntity implements ITileCraftingInjector {

    @Unique
    protected IFusionCraftingCore stellar_core$core = null;

    public MixinTileFusionCraftingInjector(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).invalidate();
        if (!StellarCore.CONFIG.BUG_FIXES.draconicEvolution.craftingInjector) {
            return;
        }
        if (this.stellar_core$core != null) {
            this.stellar_core$core.onInjectorUnload();
        }
    }

    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
        if (!StellarCore.CONFIG.BUG_FIXES.draconicEvolution.craftingInjector) {
            return;
        }
        if (this.stellar_core$core != null) {
            this.stellar_core$core.onInjectorUnload();
        }
    }

    @Override
    @SuppressWarnings("AddedMixinMembersNamePattern")
    public void onInjectorAddToCore(final IFusionCraftingCore core) {
        this.stellar_core$core = core;
    }

}