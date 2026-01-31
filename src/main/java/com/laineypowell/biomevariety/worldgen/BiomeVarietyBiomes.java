package com.laineypowell.biomevariety.worldgen;

import com.laineypowell.biomevariety.BiomeVariety;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public final class BiomeVarietyBiomes {
    public static final ResourceKey<Biome> BAOBAB_PLAINS = resourceKey("baobab_plains");
    public static final ResourceKey<Biome> ANTARCTIC = resourceKey("antarctic");
    public static final ResourceKey<Biome> REDWOOD_FOREST = resourceKey("redwood_forest");
    public static final ResourceKey<Biome> SWEETS = resourceKey("sweets");

    public static final List<ResourceKey<Biome>> BIOMES = List.of(BAOBAB_PLAINS, ANTARCTIC, REDWOOD_FOREST, SWEETS);

    public static ResourceKey<Biome> resourceKey(String name) {
        return ResourceKey.create(Registries.BIOME, BiomeVariety.resourceLocation(name));
    }
}
