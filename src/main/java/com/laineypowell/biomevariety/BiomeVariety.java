package com.laineypowell.biomevariety;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public final class BiomeVariety implements ModInitializer, TerraBlenderApi {
    @Override
    public void onInitialize() {
        BiomeVarietyBlocks.register();
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new BiomeVarietyRegion(resourceLocation("overworld-biome-variety"), 10));
    }

    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation("biome-variety", name);
    }
}
