package github.kasuminova.stellarcore.common.integration.fluxnetworks;

import mekanism.api.math.FloatingLong;
import mekanism.common.integration.energy.forgeenergy.ForgeStrictEnergyHandler;
import net.minecraft.core.Direction;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import sonar.fluxnetworks.common.device.TileFluxPlug;

//public class FluxPlugAcceptor extends ForgeStrictEnergyHandler {
//
//    private final TileFluxPlug plug;
//
//    public FluxPlugAcceptor(final TileFluxPlug plug, final Direction dir) {
//        super((IEnergyStorage) plug.getCapability(CapabilityEnergy.ENERGY, dir));
//        this.plug = plug;
//    }
//
//    @Override
//    public FloatingLong insertEnergy(final Direction dir, final double amount, final boolean simulate) {
//        double maxCanReceive = Math.min(plug.getMaxTransferLimit() - plug.getTransferBuffer(), amount / 2.5D);
//        if (maxCanReceive >= Long.MAX_VALUE) {
//            maxCanReceive = Long.MAX_VALUE;
//        }
//        return plug.getTransferHandler().receive((long) maxCanReceive, dir, simulate) * 2.5D;
//    }
//}