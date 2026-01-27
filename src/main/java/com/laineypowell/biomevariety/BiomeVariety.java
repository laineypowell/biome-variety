package com.laineypowell.biomevariety;

import com.laineypowell.biomevariety.worldgen.BiomeVarietyRegion;
import com.laineypowell.biomevariety.worldgen.BiomeVarietySurfaceRules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public final class BiomeVariety implements ModInitializer, TerraBlenderApi {
    public static final String MOD_ID = "biome-variety";

    @Override
    public void onInitialize() {
        BiomeVarietyBlocks.register();
        BiomeVarietyItems.register();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceLocation(MOD_ID), FabricItemGroup.builder()
                        .icon(BiomeVarietyItems.BAOBAB_LOG::getDefaultInstance)
                        .title(Component.translatable("itemGroup.biome-variety"))
                        .displayItems((itemDisplayParameters, output) -> {
                            output.accept(BiomeVarietyItems.BAOBAB_LOG);
                            output.accept(BiomeVarietyItems.BAOBAB_WOOD);
                            output.accept(BiomeVarietyItems.STRIPPED_BAOBAB_LOG);
                            output.accept(BiomeVarietyItems.STRIPPED_BAOBAB_WOOD);
                        })
                .build());

        log(BiomeVarietyBlocks.BAOBAB_LOG, BiomeVarietyBlocks.STRIPPED_BAOBAB_LOG);
        log(BiomeVarietyBlocks.BAOBAB_WOOD, BiomeVarietyBlocks.STRIPPED_BAOBAB_WOOD);
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new BiomeVarietyRegion(resourceLocation(MOD_ID), 10));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, BiomeVarietySurfaceRules.ruleSource());
    }

    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void log(Block log, Block strippedLog) {
        StrippableBlockRegistry.register(log, strippedLog);
        var flammableBlocks = FlammableBlockRegistry.getDefaultInstance();
        flammableBlocks.add(log, 5, 5);
        flammableBlocks.add(strippedLog, 5, 5);
    }

}
