package github.kasuminova.stellarcore;

import github.kasuminova.stellarcore.common.bugfix.TileEntityContainerFixes;
import github.kasuminova.stellarcore.common.config.StellarCoreConfig;
import github.kasuminova.stellarcore.common.integration.fluxnetworks.IntegrationsFluxNetworks;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(StellarCore.MOD_ID)
public class StellarCore {
    public static final String MOD_ID = "earthcrust";
    public static final Logger LOGGER = LogManager.getLogger();
    public static StellarCoreConfig CONFIG = new StellarCoreConfig();

    public StellarCore() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
    }

    private void init(final FMLCommonSetupEvent event) {
        AutoConfig.register(StellarCoreConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(StellarCoreConfig.class).getConfig();
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () ->
                new ConfigGuiHandler.ConfigGuiFactory((mc, parent) -> AutoConfig.getConfigScreen(StellarCoreConfig.class, parent).get())
        );

        if (ModList.get().isLoaded("fluxnetworks") && ModList.get().isLoaded("mekanism")) {
            IntegrationsFluxNetworks.initMekanismIntegration();
        }

        MinecraftForge.EVENT_BUS.register(TileEntityContainerFixes.INSTANCE);
    }
}