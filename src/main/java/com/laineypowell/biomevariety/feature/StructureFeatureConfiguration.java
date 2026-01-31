package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.WeightedList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record StructureFeatureConfiguration(WeightedList<ResourceLocation> structures) implements FeatureConfiguration {
    public static final Codec<StructureFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(WeightedList.codec(ResourceLocation.CODEC).fieldOf("structures").forGetter(StructureFeatureConfiguration::structures)).apply(instance, StructureFeatureConfiguration::new));

}
