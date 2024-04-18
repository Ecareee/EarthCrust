package github.kasuminova.stellarcore.common.integration.fluxnetworks;

import mekanism.api.Action;
import mekanism.api.energy.ISidedStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.NotNull;
import sonar.fluxnetworks.api.energy.IBlockEnergyBridge;

import javax.annotation.Nonnull;

public class MekanismEnergyHandler implements IBlockEnergyBridge {

    public static final MekanismEnergyHandler INSTANCE = new MekanismEnergyHandler();

    public static Capability<ISidedStrictEnergyHandler> ENERGY_HANDLER_CAPABILITY;

    @Override
    public boolean hasCapability(@Nonnull final BlockEntity te, final @NotNull Direction dir) {
        return canAddEnergy(te, dir) || canRemoveEnergy(te, dir);
    }

    @Override
    public boolean canAddEnergy(@Nonnull final BlockEntity te, final @NotNull Direction dir) {
//        return te instanceof ISidedStrictEnergyHandler acceptor && acceptor.canReceiveEnergy(dir);
        return te instanceof ISidedStrictEnergyHandler &&
                te.getCapability(ENERGY_HANDLER_CAPABILITY, dir)
                        .map(handler -> !handler.insertEnergy(FloatingLong.ZERO, dir, Action.SIMULATE).isZero())
                        .orElse(false);
    }

    @Override
    public boolean canRemoveEnergy(@Nonnull final BlockEntity te, final @NotNull Direction dir) {
//        return te instanceof ISidedStrictEnergyHandler outputter && outputter.canOutputEnergy(dir);
        return te instanceof ISidedStrictEnergyHandler &&
                te.getCapability(ENERGY_HANDLER_CAPABILITY, dir)
                        .map(handler -> !handler.extractEnergy(FloatingLong.ZERO, dir, Action.SIMULATE).isZero())
                        .orElse(false);
    }

    @Override
    public long addEnergy(final long amount, @Nonnull final BlockEntity te, final @NotNull Direction dir, final boolean simulate) {
        if (te instanceof ISidedStrictEnergyHandler handler) {
            return (long) Math.ceil(handler.insertEnergy(FloatingLong.create(((double) amount) * 2.5D), dir, simulate ? Action.SIMULATE : Action.EXECUTE).divide(2.5D).longValue());
        }
        return 0;
    }

    @Override
    public long removeEnergy(final long amount, @Nonnull final BlockEntity te, final @NotNull Direction dir, final boolean simulate) {
        if (te instanceof ISidedStrictEnergyHandler handler) {
            return (long) Math.ceil(handler.extractEnergy(FloatingLong.create(((double) amount) * 2.5D), dir, simulate ? Action.SIMULATE : Action.EXECUTE).divide(2.5D).longValue());
        }
        return 0;
    }
}
