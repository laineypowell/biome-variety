package com.laineypowell.biomevariety.noise;

import com.laineypowell.biomevariety.FastNoise;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public sealed interface Noise permits AddNoise, SimplexNoise, RandomNoise, SubtractNoise {
    Codec<Noise> CODEC = ResourceLocation.CODEC.dispatch(Noise::getResourceLocation, resourceLocation -> switch (resourceLocation.toString()) {
        case "biome-variety:add" -> AddNoise.CODEC;
        case "biome-variety:subtract" -> SubtractNoise.CODEC;
        case "biome-variety:random" -> RandomNoise.CODEC;
        case "biome-variety:simplex" -> SimplexNoise.CODEC;

        default -> throw new RuntimeException();
    });

    float sample(BlockPos blockPos, RandomSource randomSource, FastNoise fastNoise);

    ResourceLocation getResourceLocation();
}
