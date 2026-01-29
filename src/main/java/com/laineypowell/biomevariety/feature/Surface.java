package com.laineypowell.biomevariety.feature;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public sealed interface Surface permits SingleSurface, SplitSurface {
    Codec<Surface> CODEC = ResourceLocation.CODEC.dispatch(Surface::getResourceLocation, resourceLocation -> switch (resourceLocation.toString()) {
        case "biome-variety:single" -> SingleSurface.CODEC;
        case "biome-variety:split" -> SplitSurface.CODEC;

        default -> throw new RuntimeException();
    });

    Pair<BlockStateProvider, BlockStateProvider> getBlockStateProviders();

    ResourceLocation getResourceLocation();

}
