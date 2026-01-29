package com.laineypowell.biomevariety.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record NoiseSampleFeatureConfiguration(Surface surfaceProvider, float radius, float resolution, float grain, float threshold) implements FeatureConfiguration {
    public static final Codec<NoiseSampleFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Surface.CODEC.fieldOf("surface_provider").forGetter(NoiseSampleFeatureConfiguration::surfaceProvider),
            Codec.FLOAT.fieldOf("radius").forGetter(NoiseSampleFeatureConfiguration::radius),
            Codec.FLOAT.fieldOf("resolution").forGetter(NoiseSampleFeatureConfiguration::resolution),
            Codec.FLOAT.fieldOf("grain").forGetter(NoiseSampleFeatureConfiguration::grain),
            Codec.FLOAT.fieldOf("threshold").forGetter(NoiseSampleFeatureConfiguration::threshold)
    ).apply(instance, NoiseSampleFeatureConfiguration::new));


}
