package com.laineypowell.biomevariety.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record NoiseSampleFeatureConfiguration(BlockStateProvider blockState, BlockStateProvider blockStateBelow, float radius, float resolution, float grain, float threshold) implements FeatureConfiguration {
    public static final Codec<NoiseSampleFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("block_state").forGetter(NoiseSampleFeatureConfiguration::blockState),
            BlockStateProvider.CODEC.fieldOf("block_state_below").forGetter(NoiseSampleFeatureConfiguration::blockStateBelow),
            Codec.FLOAT.fieldOf("radius").forGetter(NoiseSampleFeatureConfiguration::radius),
            Codec.FLOAT.fieldOf("resolution").forGetter(NoiseSampleFeatureConfiguration::resolution),
            Codec.FLOAT.fieldOf("grain").forGetter(NoiseSampleFeatureConfiguration::grain),
            Codec.FLOAT.fieldOf("threshold").forGetter(NoiseSampleFeatureConfiguration::threshold)
    ).apply(instance, NoiseSampleFeatureConfiguration::new));


}
