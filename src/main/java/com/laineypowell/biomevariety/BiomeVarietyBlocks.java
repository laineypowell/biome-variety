package com.laineypowell.biomevariety;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public final class BiomeVarietyBlocks {

    public static void register() {

    }

    public static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, BiomeVariety.resourceLocation(name), block);
    }
}
