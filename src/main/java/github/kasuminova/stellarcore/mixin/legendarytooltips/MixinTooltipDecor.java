package github.kasuminova.stellarcore.mixin.legendarytooltips;

import com.anthonyhilyard.legendarytooltips.render.TooltipDecor;
import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.client.gui.Font;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.List;

@Mixin(TooltipDecor.class)
public class MixinTooltipDecor {

    /**
     * 换行有问题捏。
     */
    @Redirect(method = "drawBorder",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;split(Lnet/minecraft/network/chat/FormattedText;I)Ljava/util/List;",
                    remap = true
            ),
            remap = false)
    private static List<FormattedCharSequence> redirectDrawBorderSplit(final Font instance, final FormattedText text, final int wrapWidth) {
        if (!StellarCore.CONFIG.FEATURES.legendaryTooltips.tooltipDecor) {
            return instance.split(text, wrapWidth);
        }
        return Collections.singletonList(Language.getInstance().getVisualOrder(text));
    }

}