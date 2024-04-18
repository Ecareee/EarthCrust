package github.kasuminova.stellarcore.common.config;

import github.kasuminova.stellarcore.StellarCore;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = StellarCore.MOD_ID)
public class StellarCoreConfig implements ConfigData {

    // 如果 static，序列化就会似
    @ConfigEntry.Category("BugFixes")
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public final BugFixes BUG_FIXES = new BugFixes();

    @ConfigEntry.Category("Performance")
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public final Performance PERFORMANCE = new Performance();

    @ConfigEntry.Category("Features")
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public final Features FEATURES = new Features();

    public static class BugFixes {

//        @Config.Name("Container")
        @ConfigEntry.Gui.CollapsibleObject
        public final Container container = new Container();

//        @Config.Name("ArmourersWorkshop")
//        public final ArmourersWorkshop armourersWorkshop = new ArmourersWorkshop();

//        @Config.Name("CoFHCore")
        @ConfigEntry.Gui.CollapsibleObject
        public final CoFHCore coFHCore = new CoFHCore();

//        @Config.Name("DraconicEvolution")
        @ConfigEntry.Gui.CollapsibleObject
        public final DraconicEvolution draconicEvolution = new DraconicEvolution();

//        @Config.Name("FluxNetworks")
        @ConfigEntry.Gui.CollapsibleObject
        public final FluxNetworks fluxNetworks = new FluxNetworks();

//        @Config.Name("IndustrialCraft2")
//        public final IndustrialCraft2 industrialCraft2 = new IndustrialCraft2();

//        @Config.Name("ImmersiveEngineering")
        @ConfigEntry.Gui.CollapsibleObject
        public final ImmersiveEngineering immersiveEngineering = new ImmersiveEngineering();

//        @Config.Name("MrCrayfishFurniture")
        @ConfigEntry.Gui.CollapsibleObject
        public final MrCrayfishFurniture mrCrayfishFurniture = new MrCrayfishFurniture();

//        @Config.Name("TheOneProbe")
        @ConfigEntry.Gui.CollapsibleObject
        public final TheOneProbe theOneProbe = new TheOneProbe();

//        @Config.Name("ThermalDynamics")
        @ConfigEntry.Gui.CollapsibleObject
        public final ThermalDynamics thermalDynamics = new ThermalDynamics();

//        @Config.Name("ThermalExpansion")
        @ConfigEntry.Gui.CollapsibleObject
        public final ThermalExpansion thermalExpansion = new ThermalExpansion();

        public static class Container {

//            @Config.Name("ContainerUnloadTileEntityFixes")
            public boolean containerTileEntityFixes = true;

        }

//        public static class ArmourersWorkshop {
//            @Config.Name("SkinTextureCrashFixes")
//            public boolean skinTexture = true;
//        }

        public static class CoFHCore {

//            @Config.Name("ContainerInventoryItemFixes")
            public boolean containerInventoryItem = true;

//            @Config.Name("TileInventoryFixes")
            public boolean tileInventory = true;

        }

        public static class DraconicEvolution {

//            @Config.Name("CraftingInjectorFixes")
            public boolean craftingInjector = true;

        }

        public static class FluxNetworks {

//            @Config.Name("TheOneProbeIntegration")
            public boolean fixTop = true;

        }

//        public static class IndustrialCraft2 {
//
//            @Config.Name("GradualRecipeFixes")
//            public boolean gradualRecipe = true;
//
//        }

        public static class ImmersiveEngineering {

//            @Config.Name("MultiblockStructureContainerFixes")
            public boolean blockIEMultiblock = true;

//            @Config.Name("JerryCanFixes")
            public boolean fixJerryCanRecipe = true;

        }

        public static class MrCrayfishFurniture {

//            @Config.RequiresMcRestart
//            @Config.Name("ImageCacheCrashFixes")
            @ConfigEntry.Gui.RequiresRestart
            public boolean imageCache = true;

//            @Config.Name("RotatableFurniture")
            public boolean rotatableFurniture = true;

//            @Config.Name("BlockFurnitureTileFixes")
            public boolean blockFurnitureTile = true;
        }

        public static class TheOneProbe {

//            @Config.Name("PlayerEntityRenderFixes")
            public boolean fixRenderHelper = true;

        }

        public static class ThermalDynamics {

//            @Config.Name("FluidDuplicateFixes")
            public boolean fixFluidDuplicate = true;

        }

        public static class ThermalExpansion {

//            @Config.Name("ContainerSatchelFilterFixes")
            public boolean containerSatchelFilter = true;

        }

    }

    public static class Performance {

//        @Config.Name("Vanilla")
        @ConfigEntry.Gui.CollapsibleObject
        public final Vanilla vanilla = new Vanilla();

//        @Config.Name("Avaritia")
//        public final Avaritia avaritia = new Avaritia();

//        @Config.Name("BiomesOPlenty")
//        public final BiomesOPlenty biomesOPlenty = new BiomesOPlenty();

//        @Config.Name("BloodMagic")
        @ConfigEntry.Gui.CollapsibleObject
        public final BloodMagic bloodMagic = new BloodMagic();

//        @Config.Name("Botania")
        @ConfigEntry.Gui.CollapsibleObject
        public final Botania botania = new Botania();

//        @Config.Name("Chisel")
        @ConfigEntry.Gui.CollapsibleObject
        public final Chisel chisel = new Chisel();

//        @Config.Name("Cucumber")
        @ConfigEntry.Gui.CollapsibleObject
        public final Cucumber cucumber = new Cucumber();

//        @Config.Name("IndustrialCraft2")
//        public final IndustrialCraft2 industrialCraft2 = new IndustrialCraft2();

//        @Config.Name("Mekanism")
        @ConfigEntry.Gui.CollapsibleObject
        public final Mekanism mekanism = new Mekanism();

        public static class Vanilla {

//            @Config.RequiresMcRestart
//            @Config.Name("CapturedBlockSnapshotsImprovements")
            @ConfigEntry.Gui.RequiresRestart
            public boolean capturedBlockSnapshots = false;

//            @Config.RequiresMcRestart
//            @Config.Name("ChunkTileEntityMapImprovements")
            @ConfigEntry.Gui.RequiresRestart
            public boolean blockPos2ValueMap = true;

            /**
             * TODO 真的有用吗？
             */
//            @Config.RequiresMcRestart
//            @Config.Name("NonNullListImprovements")
            @ConfigEntry.Gui.RequiresRestart
            public boolean nonNullList = true;

        }

//        public static class Avaritia {
//
//            @Config.Name("TileBaseImprovements")
//            public boolean tileBase = true;
//
//        }
//
//        public static class BiomesOPlenty {
//
//            @Config.Name("TrailManagerAsync")
//            public boolean trailManager = true;
//
//        }

        public static class Cucumber {

//            @Config.Name("VanillaPacketDispatcherImprovements")
            public boolean vanillaPacketDispatcher = false;

//            @Config.Name("TileEntityUpdateRange")
            public float tileEntityUpdateRange = 16F;

        }

        public static class BloodMagic {

//            @Config.Name("BloodAltarImprovements")
            public boolean bloodAltar = true;

        }

        public static class Botania {

//            @Config.Name("SparkEntityImprovements")
            public boolean sparkImprovements = true;

//            @Config.RangeInt(min = 2, max = 60)
//            @Config.Name("SparkMaxWorkDelay")
            @ConfigEntry.BoundedDiscrete(min = 2, max = 60)
            public int sparkMaxWorkDelay = 10;

        }

        public static class Chisel {

//            @Config.Name("AutoChiselImprovements")
            public boolean autoChiselImprovements = true;

//            @Config.RangeInt(min = 20, max = 100)
//            @Config.Name("AutoChiselMaxWorkDelay")
            @ConfigEntry.BoundedDiscrete(min = 20, max = 100)
            public int autoChiselMaxWorkDelay = 100;

        }

//        public static class IndustrialCraft2 {
//
//            @Config.Name("EnergyCalculatorLegImprovements")
//            public boolean energyCalculatorLeg = true;
//
//            @Config.Name("GridImprovements")
//            public boolean grid = true;
//
//            @Config.Name("ItemUpgradeModuleImprovements")
//            public boolean itemUpgradeModule = false;
//
//            @Config.Name("ItemUpgradeModuleWorkDelay")
//            public int itemUpgradeModuleWorkDelay = 5;
//
//        }

        public static class Mekanism {

//            @Config.Name("PipeUtilsImprovements")
            public boolean pipeUtils = true;

//            @Config.Name("EnergyNetworkImprovements")
            public boolean energyNetwork = true;

//            @Config.Name("FrequencyImprovements")
            public boolean frequency = true;

        }

    }

    public static class Features {
//        @Config.Name("EnableCustomGameTitle")
//        public boolean enableTitle = false;
//
//        @Config.Name("TitleUseHitokotoAPI")
//        public boolean hitokoto = true;
//
//        @Config.Name("CustomGameTitle")
//        public String title = "Minecraft 1.12.2";

//        @Config.Name("FontScale")
        @ConfigEntry.Gui.CollapsibleObject
        public final FontScale fontScale = new FontScale();

//        @Config.Name("Botania")
        @ConfigEntry.Gui.CollapsibleObject
        public final Botania botania = new Botania();

//        @Config.Name("LegendaryTooltips")
        @ConfigEntry.Gui.CollapsibleObject
        public final LegendaryTooltips legendaryTooltips = new LegendaryTooltips();

//        @Config.Name("Mekanism")
        @ConfigEntry.Gui.CollapsibleObject
        public final Mekanism mekanism = new Mekanism();

//        @Config.Name("NuclearCraftOverhauled")
//        public final NuclearCraftOverhauled nuclearCraftOverhauled = new NuclearCraftOverhauled();

        public static class FontScale {

//            @Config.RangeDouble(min = 0F, max = 1.0F)
//            @Config.Name("AppliedEnergetics2")
            @ConfigEntry.BoundedDiscrete(min = (long) 0F, max = (long) 1.0F)
            public float ae2 = 0.5F;

        }

        public static class Botania {

//            @Config.Name("DisableCosmeticRecipe")
            public boolean disableCosmeticRecipe = false;

        }

        public static class LegendaryTooltips {

//            @Config.Name("DisableTitleWrap")
            public boolean tooltipDecor = false;

        }

        public static class Mekanism {

//            @Config.RequiresMcRestart
//            @Config.Name("TOPSupport")
            @ConfigEntry.Gui.RequiresRestart
            public boolean topSupport = true;

//            @Config.Name("FluxNetworksSupport")
            public boolean fluxNetworksSupport = true;

        }

    }

}