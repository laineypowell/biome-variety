package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVariety;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record SingleSurface(BlockStateProvider blockState) implements Surface {
    public static final Codec<SingleSurface> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.CODEC.fieldOf("block_state").forGetter(SingleSurface::blockState)).apply(instance, SingleSurface::new));

    @Override
    public Pair<BlockStateProvider, BlockStateProvider> getBlockStateProviders() {
        return Pair.of(blockState, blockState);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return BiomeVariety.resourceLocation("single");
    }
}
