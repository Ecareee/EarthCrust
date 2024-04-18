package github.kasuminova.stellarcore.mixin.minecraft.chunk;

import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.core.Registry;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelChunk.class)
public abstract class MixinLevelChunk extends ChunkAccess {
    // ？
//    @Shadow @Final @Mutable protected Map<BlockPos, BlockEntity> blockEntities;

    public MixinLevelChunk(ChunkPos pChunkPos, UpgradeData pUpgradeData, LevelHeightAccessor pLevelHeightAccessor, Registry<Biome> pBiomeRegistry, long pInhabitedTime, @Nullable LevelChunkSection[] pSections, @Nullable BlendingData pBlendingData) {
        super(pChunkPos, pUpgradeData, pLevelHeightAccessor, pBiomeRegistry, pInhabitedTime, pSections, pBlendingData);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/ChunkPos;)V", at = @At("RETURN"))
    private void injectInit(final Level worldIn, final ChunkPos chunkPos, final CallbackInfo ci) {
        if (!StellarCore.CONFIG.PERFORMANCE.vanilla.blockPos2ValueMap) {
            return;
        }
//        this.blockEntities = new BlockPos2ValueMap<>();
    }

}