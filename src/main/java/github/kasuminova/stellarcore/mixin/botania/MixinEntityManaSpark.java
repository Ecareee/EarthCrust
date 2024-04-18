package github.kasuminova.stellarcore.mixin.botania;

import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.entity.EntityManaSpark;

@Mixin(EntityManaSpark.class)
public abstract class MixinEntityManaSpark {

    @Unique private boolean stellarcore$receiveLeastOne = false;
    @Unique private int stellarcore$failureCounter = 1;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lvazkii/botania/common/entity/EntityManaSpark;getUpgrade()Lvazkii/botania/api/mana/spark/SparkUpgradeType;",
                    remap = false
            ),
            cancellable = true
    )
    private void injectTickPre(final CallbackInfo ci) {
        if (!StellarCore.CONFIG.PERFORMANCE.botania.sparkImprovements) {
            return;
        }
        if (((Entity) (Object) this).level.getGameTime() % stellarcore$failureCounter != 0) {
            ci.cancel();
        }
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/mana/IManaReceiver;receiveMana(I)V", remap = false))
    private void redirectReceiveMana(final IManaReceiver receiver, final int mana) {
        receiver.receiveMana(mana);
        if (!StellarCore.CONFIG.PERFORMANCE.botania.sparkImprovements) {
            return;
        }
        if (mana != 0) {
            stellarcore$receiveLeastOne = true;
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTickTail(final CallbackInfo ci) {
        if (!StellarCore.CONFIG.PERFORMANCE.botania.sparkImprovements) {
            return;
        }
        if (stellarcore$receiveLeastOne) {
            if (stellarcore$failureCounter > 1) {
                stellarcore$failureCounter--;
            }
        } else if (stellarcore$failureCounter < StellarCore.CONFIG.PERFORMANCE.botania.sparkMaxWorkDelay) {
            stellarcore$failureCounter++;
        }
    }

}