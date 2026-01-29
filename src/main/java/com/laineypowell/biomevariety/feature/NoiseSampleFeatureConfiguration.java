package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.noise.Noise;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record NoiseSampleFeatureConfiguration(Surface surface, BlockPredicate predicate, Noise noise, float radius, float threshold) implements FeatureConfiguration {
    public static final Codec<NoiseSampleFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Surface.CODEC.fieldOf("surface").forGetter(NoiseSampleFeatureConfiguration::surface),
            BlockPredicate.CODEC.fieldOf("predicate").forGetter(NoiseSampleFeatureConfiguration::predicate),
            Noise.CODEC.fieldOf("noise").forGetter(NoiseSampleFeatureConfiguration::noise),
            Codec.FLOAT.fieldOf("radius").forGetter(NoiseSampleFeatureConfiguration::radius),
            Codec.FLOAT.fieldOf("threshold").forGetter(NoiseSampleFeatureConfiguration::threshold)
    ).apply(instance, NoiseSampleFeatureConfiguration::new));


}
