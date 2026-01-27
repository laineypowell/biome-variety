package com.laineypowell.biomevariety;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class BiomeVarietyBlocks {
    public static final Block BAOBAB_LOG = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block BAOBAB_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block STRIPPED_BAOBAB_LOG = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block STRIPPED_BAOBAB_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));

    public static void register() {
        register("baobab_log", BAOBAB_LOG);
        register("baobab_wood", BAOBAB_WOOD);
        register("stripped_baobab_log", STRIPPED_BAOBAB_LOG);
        register("stripped_baobab_wood", STRIPPED_BAOBAB_WOOD);
    }

    public static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, BiomeVariety.resourceLocation(name), block);
    }
}
