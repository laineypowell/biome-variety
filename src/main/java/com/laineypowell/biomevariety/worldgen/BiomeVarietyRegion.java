package com.laineypowell.biomevariety.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public final class BiomeVarietyRegion extends Region {

    public BiomeVarietyRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addBiome(mapper, ParameterUtils.Temperature.WARM.parameter(), ParameterUtils.Humidity.NEUTRAL.parameter(), ParameterUtils.Continentalness.INLAND.parameter(), ParameterUtils.Erosion.EROSION_3.parameter(), ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Depth.SURFACE.parameter(), 0.0f, BiomeVarietyBiomes.BAOBAB_PLAINS);
    }
}
