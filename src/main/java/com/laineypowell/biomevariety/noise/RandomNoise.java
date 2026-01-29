package com.laineypowell.biomevariety.noise;

import com.laineypowell.biomevariety.BiomeVariety;
import com.laineypowell.biomevariety.FastNoise;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public record RandomNoise(float scalar) implements Noise {
    public static final Codec<RandomNoise> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.FLOAT.fieldOf("scalar").forGetter(RandomNoise::scalar)).apply(instance, RandomNoise::new));

    @Override
    public float sample(BlockPos blockPos, RandomSource randomSource, FastNoise fastNoise) {
        return randomSource.nextFloat() * scalar;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return BiomeVariety.resourceLocation("random");
    }
}
