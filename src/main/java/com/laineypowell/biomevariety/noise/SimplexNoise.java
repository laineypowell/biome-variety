package com.laineypowell.biomevariety.noise;

import com.laineypowell.biomevariety.BiomeVariety;
import com.laineypowell.biomevariety.FastNoise;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public record SimplexNoise(float grain) implements Noise {
    public static final Codec<SimplexNoise> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.FLOAT.fieldOf("grain").forGetter(SimplexNoise::grain)).apply(instance, SimplexNoise::new));

    @Override
    public float sample(BlockPos blockPos, RandomSource randomSource, FastNoise fastNoise) {
        return fastNoise.GetNoise(
                ((float) blockPos.getX() / 2.0f) * grain,
                ((float) blockPos.getY() / 2.0f) * grain,
                ((float) blockPos.getZ() / 2.0f) * grain);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return BiomeVariety.resourceLocation("simplex");
    }
}
