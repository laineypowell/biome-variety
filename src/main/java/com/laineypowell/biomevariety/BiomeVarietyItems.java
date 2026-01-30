package com.laineypowell.biomevariety;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class BiomeVarietyItems {
    public static final Item BAOBAB_LOG = blockItem(BiomeVarietyBlocks.BAOBAB_LOG);
    public static final Item BAOBAB_WOOD = blockItem(BiomeVarietyBlocks.BAOBAB_WOOD);
    public static final Item STRIPPED_BAOBAB_LOG = blockItem(BiomeVarietyBlocks.STRIPPED_BAOBAB_LOG);
    public static final Item STRIPPED_BAOBAB_WOOD = blockItem(BiomeVarietyBlocks.STRIPPED_BAOBAB_WOOD);
    public static final Item BAOBAB_LOG_WEDGE = blockItem(BiomeVarietyBlocks.BAOBAB_LOG_WEDGE);
    public static final Item STRIPPED_BAOBAB_LOG_WEDGE = blockItem(BiomeVarietyBlocks.STRIPPED_BAOBAB_LOG_WEDGE);

    public static final Item PATAGONIAN_OAK_LOG = blockItem(BiomeVarietyBlocks.PATAGONIAN_OAK_LOG);
    public static final Item PATAGONIAN_OAK_WOOD = blockItem(BiomeVarietyBlocks.PATAGONIAN_OAK_WOOD);

    public static final Item PATAGONIAN_OAK_LOG_BRANCH = blockItem(BiomeVarietyBlocks.PATAGONIAN_OAK_LOG_BRANCH);
    public static final Item STRIPPED_PATAGONIAN_OAK_LOG_BRANCH = blockItem(BiomeVarietyBlocks.STRIPPED_PATAGONIAN_OAK_LOG_BRANCH);

    public static final Item GRASSY_DUNE_SAND = blockItem(BiomeVarietyBlocks.GRASSY_DUNE_SAND);
    public static final Item DUNE_SAND = blockItem(BiomeVarietyBlocks.DUNE_SAND);
    public static final Item DUNE_SAND_PATH = blockItem(BiomeVarietyBlocks.DUNE_SAND_PATH);
    public static final Item DUNE_SAND_FARMLAND = blockItem(BiomeVarietyBlocks.DUNE_SAND_FARMLAND);

    public static final Item SNOWY_ANTARCTIC_ICE = blockItem(BiomeVarietyBlocks.SNOWY_ANTARCTIC_ICE);
    public static final Item ANTARCTIC_ICE = blockItem(BiomeVarietyBlocks.ANTARCTIC_ICE);
    public static final Item ANTARCTIC_ICE_PATH = blockItem(BiomeVarietyBlocks.ANTARCTIC_ICE_PATH);
    public static final Item ANTARCTIC_ICE_FARMLAND = blockItem(BiomeVarietyBlocks.ANTARCTIC_ICE_FARMLAND);

    public static final Item GRASSY_WEATHERED_DIRT = blockItem(BiomeVarietyBlocks.GRASSY_WEATHERED_DIRT);
    public static final Item WEATHERED_DIRT = blockItem(BiomeVarietyBlocks.WEATHERED_DIRT);
    public static final Item WEATHERED_DIRT_PATH = blockItem(BiomeVarietyBlocks.WEATHERED_DIRT_PATH);
    public static final Item WEATHERED_DIRT_FARMLAND = blockItem(BiomeVarietyBlocks.WEATHERED_DIRT_FARMLAND);

    public static final Item SAVANNA_GRASS = blockItem(BiomeVarietyBlocks.SAVANNA_GRASS);
    public static final Item DRY_LEAVES = blockItem(BiomeVarietyBlocks.DRY_LEAVES);
    public static final Item BUTTONWEED = blockItem(BiomeVarietyBlocks.BUTTONWEED);
    public static final Item VIOLET = blockItem(BiomeVarietyBlocks.VIOLET);

    public static void register() {
        register("baobab_log", BAOBAB_LOG);
        register("baobab_wood", BAOBAB_WOOD);
        register("stripped_baobab_log", STRIPPED_BAOBAB_LOG);
        register("stripped_baobab_wood", STRIPPED_BAOBAB_WOOD);
        register("baobab_log_wedge", BAOBAB_LOG_WEDGE);
        register("stripped_baobab_log_wedge", STRIPPED_BAOBAB_LOG_WEDGE);

        register("patagonian_oak_log", PATAGONIAN_OAK_LOG);
        register("patagonian_oak_wood", PATAGONIAN_OAK_WOOD);
        register("patagonian_oak_log_branch", PATAGONIAN_OAK_LOG_BRANCH);
        register("stripped_patagonian_oak_log_branch", STRIPPED_PATAGONIAN_OAK_LOG_BRANCH);

        register("grassy_dune_sand", GRASSY_DUNE_SAND);
        register("dune_sand", DUNE_SAND);
        register("dune_sand_path", DUNE_SAND_PATH);
        register("dune_sand_farmland", DUNE_SAND_FARMLAND);

        register("snowy_antarctic_ice", SNOWY_ANTARCTIC_ICE);
        register("antarctic_ice", ANTARCTIC_ICE);
        register("antarctic_ice_path", ANTARCTIC_ICE_PATH);
        register("antarctic_ice_farmland", ANTARCTIC_ICE_FARMLAND);

        register("grassy_weathered_dirt", GRASSY_WEATHERED_DIRT);
        register("weathered_dirt", WEATHERED_DIRT);
        register("weathered_dirt_path", WEATHERED_DIRT_PATH);
        register("weathered_dirt_farmland", WEATHERED_DIRT_FARMLAND);

        register("savanna_grass", SAVANNA_GRASS);
        register("dry_leaves", DRY_LEAVES);
        register("buttonweed", BUTTONWEED);
        register("violet", VIOLET);
    }

    public static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, BiomeVariety.resourceLocation(name), item);
    }

    public static Item blockItem(Block block) {
        return new BlockItem(block, new Item.Properties());
    }
}
