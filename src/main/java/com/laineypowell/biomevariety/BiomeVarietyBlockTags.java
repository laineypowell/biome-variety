package com.laineypowell.biomevariety;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class BiomeVarietyBlockTags {
    public static final TagKey<Block> SHORT_GRASS_GROWS_ON = tagKey("short_grass_grows_on");

    public static TagKey<Block> tagKey(String name) {
        return TagKey.create(Registries.BLOCK, BiomeVariety.resourceLocation(name));
    }
}
