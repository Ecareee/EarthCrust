package github.kasuminova.stellarcore.common.bugfix;

import github.kasuminova.stellarcore.StellarCore;
import github.kasuminova.stellarcore.common.util.ContainerTECache;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = StellarCore.MOD_ID)
@SuppressWarnings("MethodMayBeStatic")
public class TileEntityContainerFixes {

    public static final TileEntityContainerFixes INSTANCE = new TileEntityContainerFixes();

    @SubscribeEvent
    @SuppressWarnings("ConstantValue")
    public void onPlayerTick(final TickEvent.PlayerTickEvent event) {
        if (!StellarCore.CONFIG.BUG_FIXES.container.containerTileEntityFixes) {
            return;
        }
        if (event.type != TickEvent.Type.SERVER) {
            return;
        }
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        Player player = event.player;
        AbstractContainerMenu current = player.containerMenu;
        if (current == null) {
            return;
        }

        List<BlockEntity> teList = ContainerTECache.getTileEntityList(current);
        for (final BlockEntity te : teList) {
            BlockPos pos = te.getBlockPos();
            Level world = te.getLevel();
            if (te.isRemoved() || (world != null && !world.isLoaded(pos))) {
                player.closeContainer();
                StellarCore.LOGGER.warn(String.format(
                        "Detected invalid TileEntity GUI, World: %s, Pos: %s, GUI Class: %s, TileEntity Class: %s",
                        world == null ? "null" : world.dimension().location().toString(),
                        posToString(pos),
                        current.getClass().getName(),
                        te.getClass().getName()
                ));
                break;
            }
        }
    }

    public static String posToString(Vec3i pos) {
        return String.format("X:%s Y:%s Z:%s", pos.getX(), pos.getY(), pos.getZ());
    }

}