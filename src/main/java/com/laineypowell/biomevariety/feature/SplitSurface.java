package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVariety;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record SplitSurface(BlockStateProvider onFloor, BlockStateProvider underFloor) implements Surface {
    public static final Codec<SplitSurface> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("on_floor").forGetter(SplitSurface::onFloor),
            BlockStateProvider.CODEC.fieldOf("under_floor").forGetter(SplitSurface::underFloor)
    ).apply(instance, SplitSurface::new));

    @Override
    public Pair<BlockStateProvider, BlockStateProvider> getBlockStateProviders() {
        return Pair.of(onFloor, underFloor);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return BiomeVariety.resourceLocation("split");
    }
}
