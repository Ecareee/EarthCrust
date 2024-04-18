package github.kasuminova.stellarcore.mixin.minecraft.world;

import github.kasuminova.stellarcore.StellarCore;
import github.kasuminova.stellarcore.common.util.LinkedFakeArrayList;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.BlockSnapshot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(Level.class)
public class MixinLevel {

    @Shadow(remap = false) public ArrayList<BlockSnapshot> capturedBlockSnapshots;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectInit(final CallbackInfo ci) {
        if (!StellarCore.CONFIG.PERFORMANCE.vanilla.capturedBlockSnapshots) {
            return;
        }
        this.capturedBlockSnapshots = new LinkedFakeArrayList<>();
    }

}