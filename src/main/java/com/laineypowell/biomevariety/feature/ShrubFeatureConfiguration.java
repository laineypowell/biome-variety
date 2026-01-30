package com.laineypowell.biomevariety.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record ShrubFeatureConfiguration(BlockStateProvider log, BlockStateProvider leaves) implements FeatureConfiguration {
    public static final Codec<ShrubFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("log").forGetter(ShrubFeatureConfiguration::log),
            BlockStateProvider.CODEC.fieldOf("leaves").forGetter(ShrubFeatureConfiguration::leaves)
    ).apply(instance, ShrubFeatureConfiguration::new));

}
