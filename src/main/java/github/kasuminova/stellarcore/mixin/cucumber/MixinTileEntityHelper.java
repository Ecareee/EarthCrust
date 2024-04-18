package github.kasuminova.stellarcore.mixin.cucumber;

import com.blakebr0.cucumber.helper.TileEntityHelper;
import github.kasuminova.stellarcore.StellarCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TileEntityHelper.class)
public class MixinTileEntityHelper {

    @ModifyConstant(method = "isPlayerNearby", constant = @Constant(doubleValue = 64.0), remap = false)
    private static double modifyRange(final double constant) {
        if (!StellarCore.CONFIG.PERFORMANCE.cucumber.vanillaPacketDispatcher) {
            return constant;
        }
        return StellarCore.CONFIG.PERFORMANCE.cucumber.tileEntityUpdateRange;
    }

}