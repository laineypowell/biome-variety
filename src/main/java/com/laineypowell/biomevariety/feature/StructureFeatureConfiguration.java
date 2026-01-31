package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.LenientListCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public record StructureFeatureConfiguration(List<ResourceLocation> structures) implements FeatureConfiguration {
    public static final Codec<StructureFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(LenientListCodec.lenientList(ResourceLocation.CODEC).fieldOf("structures").forGetter(StructureFeatureConfiguration::structures)).apply(instance, StructureFeatureConfiguration::new));

}
