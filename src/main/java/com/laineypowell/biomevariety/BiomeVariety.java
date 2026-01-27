package com.laineypowell.biomevariety;

import com.laineypowell.biomevariety.worldgen.BiomeVarietyRegion;
import com.laineypowell.biomevariety.worldgen.BiomeVarietySurfaceRules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new BiomeVarietyRegion(resourceLocation(MOD_ID), 10));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, BiomeVarietySurfaceRules.ruleSource());
    }

    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
