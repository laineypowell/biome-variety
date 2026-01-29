package com.laineypowell.biomevariety.noise;

import com.laineypowell.biomevariety.BiomeVariety;
import com.laineypowell.biomevariety.FastNoise;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public record SimplexNoise(float grain, float scalar) implements Noise {
    public static final Codec<SimplexNoise> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("grain").forGetter(SimplexNoise::grain),
            Codec.FLOAT.optionalFieldOf("scalar", 2.0f).forGetter(SimplexNoise::scalar)
    ).apply(instance, SimplexNoise::new));

    @Override
    public float sample(BlockPos blockPos, RandomSource randomSource, FastNoise fastNoise) {
        return fastNoise.GetNoise(
                ((float) blockPos.getX() / scalar) * grain,
                ((float) blockPos.getY() / scalar) * grain,
                ((float) blockPos.getZ() / scalar) * grain);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return BiomeVariety.resourceLocation("simplex");
    }
}
