package com.laineypowell.biomevariety;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class BiomeVarietyItemTags {
    public static final TagKey<Item> BAOBAB_LOGS = tagKey("baobab_logs");
    public static final TagKey<Item> PATAGONIAN_OAK_LOGS = tagKey("patagonian_oak_logs");
    public static final TagKey<Item> REDWOOD_LOGS = tagKey("redwood_logs");
    public static final TagKey<Item> DUNE_SAND = tagKey("dune_sand");
    public static final TagKey<Item> ANTARCTIC_ICE = tagKey("antarctic_ice");
    public static final TagKey<Item> WEATHERED_DIRT = tagKey("weathered_dirt");

    public static TagKey<Item> tagKey(String name) {
        return TagKey.create(Registries.ITEM, BiomeVariety.resourceLocation(name));
    }
}
