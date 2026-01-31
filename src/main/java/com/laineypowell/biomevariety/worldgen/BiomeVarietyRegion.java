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
        addBiome(mapper, ParameterUtils.Temperature.WARM.parameter(), ParameterUtils.Humidity.ARID.parameter(), ParameterUtils.Continentalness.MID_INLAND.parameter(), ParameterUtils.Erosion.EROSION_4.parameter(), ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Depth.SURFACE.parameter(), 0.0f, BiomeVarietyBiomes.BAOBAB_PLAINS);
        addBiome(mapper, ParameterUtils.Temperature.FROZEN.parameter(), ParameterUtils.Humidity.DRY.parameter(), ParameterUtils.Continentalness.NEAR_INLAND.parameter(), ParameterUtils.Erosion.EROSION_0.parameter(), ParameterUtils.Weirdness.PEAK_VARIANT.parameter(), ParameterUtils.Depth.SURFACE.parameter(), 0.0f, BiomeVarietyBiomes.ANTARCTIC);
        addBiome(mapper, ParameterUtils.Temperature.span(ParameterUtils.Temperature.NEUTRAL, ParameterUtils.Temperature.COOL), ParameterUtils.Humidity.NEUTRAL.parameter(), ParameterUtils.Continentalness.MID_INLAND.parameter(), ParameterUtils.Erosion.span(ParameterUtils.Erosion.EROSION_1, ParameterUtils.Erosion.EROSION_3), ParameterUtils.Weirdness.PEAK_NORMAL.parameter(), ParameterUtils.Depth.SURFACE.parameter(), 0.0f, BiomeVarietyBiomes.REDWOOD_FOREST);
        addBiome(mapper, ParameterUtils.Temperature.span(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.ICY), ParameterUtils.Humidity.NEUTRAL.parameter(), ParameterUtils.Continentalness.INLAND.parameter(), ParameterUtils.Erosion.EROSION_3.parameter(), ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING.parameter(), ParameterUtils.Depth.SURFACE.parameter(), 0.0f, BiomeVarietyBiomes.SWEETS);
    }
}
