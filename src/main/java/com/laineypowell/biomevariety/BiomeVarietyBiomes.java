package com.laineypowell.biomevariety;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public final class BiomeVarietyBiomes {
    public static final ResourceKey<Biome> BAOBAB_PLAINS = resourceKey("baobab_plains");

    public static ResourceKey<Biome> resourceKey(String name) {
        return ResourceKey.create(Registries.BIOME, BiomeVariety.resourceLocation(name));
    }
}
