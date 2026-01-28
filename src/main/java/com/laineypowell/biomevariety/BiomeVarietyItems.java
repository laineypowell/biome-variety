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
    public static final Item BUTTONWEED = blockItem(BiomeVarietyBlocks.BUTTONWEED);

    public static final Item SILT_GRASS_BLOCK = blockItem(BiomeVarietyBlocks.SILT_GRASS_BLOCK);
    public static final Item SILT = blockItem(BiomeVarietyBlocks.SILT);
    public static final Item SILT_PATH = blockItem(BiomeVarietyBlocks.SILT_PATH);
    public static final Item SILT_FARM = blockItem(BiomeVarietyBlocks.SILT_FARM);

    public static final Item SNOWY_ANTARCTIC_ICE = blockItem(BiomeVarietyBlocks.SNOWY_ANTARCTIC_ICE);
    public static final Item ANTARCTIC_ICE = blockItem(BiomeVarietyBlocks.ANTARCTIC_ICE);
    public static final Item ANTARCTIC_ICE_PATH = blockItem(BiomeVarietyBlocks.ANTARCTIC_ICE_PATH);
    public static final Item ANTARCTIC_ICE_FARMLAND = blockItem(BiomeVarietyBlocks.ANTARCTIC_ICE_FARMLAND);

    public static void register() {
        register("baobab_log", BAOBAB_LOG);
        register("baobab_wood", BAOBAB_WOOD);
        register("stripped_baobab_log", STRIPPED_BAOBAB_LOG);
        register("stripped_baobab_wood", STRIPPED_BAOBAB_WOOD);
        register("baobab_log_wedge", BAOBAB_LOG_WEDGE);
        register("stripped_baobab_log_wedge", STRIPPED_BAOBAB_LOG_WEDGE);
        register("buttonweed", BUTTONWEED);

        register("silt_grass_block", SILT_GRASS_BLOCK);
        register("silt", SILT);
        register("silt_path", SILT_PATH);
        register("silt_farm", SILT_FARM);

        register("snowy_antarctic_ice", SNOWY_ANTARCTIC_ICE);
        register("antarctic_ice", ANTARCTIC_ICE);
        register("antarctic_ice_path", ANTARCTIC_ICE_PATH);
        register("antarctic_ice_farmland", ANTARCTIC_ICE_FARMLAND);
    }

    public static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, BiomeVariety.resourceLocation(name), item);
    }

    public static Item blockItem(Block block) {
        return new BlockItem(block, new Item.Properties());
    }
}
