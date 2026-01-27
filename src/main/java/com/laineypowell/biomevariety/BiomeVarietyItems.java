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

    public static void register() {
        register("baobab_log", BAOBAB_LOG);
        register("baobab_wood", BAOBAB_WOOD);
        register("stripped_baobab_log", STRIPPED_BAOBAB_LOG);
        register("stripped_baobab_wood", STRIPPED_BAOBAB_WOOD);
        register("baobab_log_wedge", BAOBAB_LOG_WEDGE);
        register("stripped_baobab_log_wedge", STRIPPED_BAOBAB_LOG_WEDGE);
    }

    public static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, BiomeVariety.resourceLocation(name), item);
    }

    public static Item blockItem(Block block) {
        return new BlockItem(block, new Item.Properties());
    }
}
