package github.kasuminova.stellarcore.mixinutil;

import net.minecraft.world.item.ItemStack;

public interface CachedItemConduit {

    ItemStack getCachedStack();

    void setCachedStack(final ItemStack cachedStack);

}