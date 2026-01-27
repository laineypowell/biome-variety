package com.laineypowell.biomevariety;

import net.minecraft.resources.ResourceLocation;
import terrablender.api.Region;
import terrablender.api.RegionType;

public final class BiomeVarietyRegion extends Region {
    public BiomeVarietyRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }
}
