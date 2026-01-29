package com.laineypowell.biomevariety.noise;

import com.laineypowell.biomevariety.BiomeVariety;
import com.laineypowell.biomevariety.FastNoise;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public record SubtractNoise(Noise operand, Noise subtract) implements Noise {
    public static final Codec<SubtractNoise> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Noise.CODEC.fieldOf("operand").forGetter(SubtractNoise::operand),
            Noise.CODEC.fieldOf("subtract").forGetter(SubtractNoise::subtract)
    ).apply(instance, SubtractNoise::new));

    @Override
    public float sample(BlockPos blockPos, RandomSource randomSource, FastNoise fastNoise) {
        return operand.sample(blockPos, randomSource, fastNoise) - subtract.sample(blockPos, randomSource, fastNoise);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return BiomeVariety.resourceLocation("subtract");
    }
}
