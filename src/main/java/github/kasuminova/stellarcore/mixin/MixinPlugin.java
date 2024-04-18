package github.kasuminova.stellarcore.mixin;

import github.kasuminova.stellarcore.StellarCore;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.*;
import java.util.function.BooleanSupplier;

public class MixinPlugin implements IMixinConfigPlugin {

    public static final Logger LOG = LogManager.getLogger("STELLAR_CORE");
    public static final String LOG_PREFIX = "[STELLAR_CORE]" + ' ';
    private static final Map<String, BooleanSupplier> MIXIN_CONFIGS = new LinkedHashMap<>();

    @Override
    public void onLoad(String mixinPackage) {
        addMixinCFG("github.kasuminova.stellarcore.mixin.minecraft.chunk.MixinLevelChunk", () -> StellarCore.CONFIG.PERFORMANCE.vanilla.blockPos2ValueMap);
        addMixinCFG("github.kasuminova.stellarcore.mixin.minecraft.world.MixinLevel", () -> StellarCore.CONFIG.PERFORMANCE.vanilla.capturedBlockSnapshots);
        addMixinCFG("github.kasuminova.stellarcore.mixin.minecraft.nnlist.MixinNonNullList", () -> StellarCore.CONFIG.PERFORMANCE.vanilla.nonNullList);
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.bloodmagic.MixinBloodAltar", "bloodmagic");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.botania.MixinCosmeticAttachRecipe", "botania");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.botania.MixinCosmeticRemoveRecipe", "botania");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.botania.MixinEntityManaSpark", "botania");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.cfm.MixinBlockFurnitureTile", "cfm");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.cucumber.MixinTileEntityHelper", "cucumber");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.draconicevolution.MixinTileCraftingInjector", "draconicevolution");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.draconicevolution.MixinTileFusionCraftingCore", "draconicevolution");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.immersiveengineering.MixinIEEntityBlock", "immersiveengineering");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.immersiveengineering.MixinJerrycanRefillRecipe", "immersiveengineering");
        addModdedMixinCFG("github.kasuminova.stellarcore.mixin.legendarytooltips.MixinTooltipDecor", "legendarytooltips");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        BooleanSupplier supplier = MIXIN_CONFIGS.get(mixinClassName);
        boolean shouldApply = MIXIN_CONFIGS.getOrDefault(mixinClassName, () -> true).getAsBoolean();
        if (supplier == null) {
            LOG.warn(LOG_PREFIX + "Mixin config {} is not found in config map! It will never be loaded.", mixinClassName);
        }
        if (shouldApply) {
            LOG.info(LOG_PREFIX + "Mixing {} into {}", mixinClassName, targetClassName);
        }
        return shouldApply;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
//        return new ArrayList<>(MIXIN_CONFIGS.keySet());
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean modLoaded(final String modID) {
        if (ModList.get() == null) {
            // 此时的 ModList 还未初始化
            return FMLLoader.getLoadingModList().getModFileById(modID) != null;
        }
        return ModList.get().isLoaded(modID);
    }

    private static void addModdedMixinCFG(final String mixinConfig, final String modID) {
        MIXIN_CONFIGS.put(mixinConfig, () -> modLoaded(modID));
    }

    private static void addModdedMixinCFG(final String mixinConfig, final String modID, final BooleanSupplier condition) {
        MIXIN_CONFIGS.put(mixinConfig, () -> modLoaded(modID) && condition.getAsBoolean());
    }

    private static void addModdedMixinCFG(final String mixinConfig, final String[] modIDs, final BooleanSupplier condition) {
        MIXIN_CONFIGS.put(mixinConfig, () -> Arrays.stream(modIDs).allMatch(ModList.get()::isLoaded) && condition.getAsBoolean());
    }

    private static void addModdedMixinCFG(final String mixinConfig, final String modID, final String... modIDs) {
        MIXIN_CONFIGS.put(mixinConfig, () -> modLoaded(modID) && Arrays.stream(modIDs).allMatch(ModList.get()::isLoaded));
    }

    private static void addMixinCFG(final String mixinConfig) {
        MIXIN_CONFIGS.put(mixinConfig, () -> true);
    }

    private static void addMixinCFG(final String mixinConfig, final BooleanSupplier conditions) {
        MIXIN_CONFIGS.put(mixinConfig, conditions);
    }
}