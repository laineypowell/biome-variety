package com.laineypowell.biomevariety.noise;

import com.laineypowell.biomevariety.BiomeVariety;
import com.laineypowell.biomevariety.FastNoise;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

import java.util.List;

public record AddNoise(List<Noise> layered) implements Noise {
    public static final Codec<AddNoise> CODEC = RecordCodecBuilder.create(instance -> instance.group(Noise.CODEC.listOf().fieldOf("layered").forGetter(AddNoise::layered)).apply(instance, AddNoise::new));

    @Override
    public float sample(BlockPos blockPos, RandomSource randomSource, FastNoise fastNoise) {
        var result = 0.0f;
        for (var noise : layered) {
            result += noise.sample(blockPos, randomSource, fastNoise);
        }

        return result;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return BiomeVariety.resourceLocation("add");
    }
}
