package com.laineypowell.biomevariety.worldgen;

import com.laineypowell.biomevariety.BiomeVariety;
import com.laineypowell.biomevariety.feature.BaobabTreeFeature;
import com.laineypowell.biomevariety.feature.ButtonweedFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class BiomeVarietyFeatures {
    public static final Feature<NoneFeatureConfiguration> BAOBAB_TREE = new BaobabTreeFeature();
    public static final Feature<NoneFeatureConfiguration> FLOWER = new ButtonweedFeature();

    public static void register() {
        register("baobab_tree", BAOBAB_TREE);
        register("flower", FLOWER);
    }

    public static void register(String name, Feature<? extends FeatureConfiguration> feature) {
        Registry.register(BuiltInRegistries.FEATURE, BiomeVariety.resourceLocation(name), feature);
    }

}
