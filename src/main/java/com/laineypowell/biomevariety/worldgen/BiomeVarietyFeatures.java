package com.laineypowell.biomevariety.worldgen;

import com.laineypowell.biomevariety.BiomeVariety;
import com.laineypowell.biomevariety.feature.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class BiomeVarietyFeatures {
    public static final Feature<NoneFeatureConfiguration> BAOBAB_TREE = new BaobabTreeFeature();
    public static final Feature<NoneFeatureConfiguration> PATAGONIAN_OAK_TREE = new PatagonianOakTreeFeature();
    public static final Feature<NoneFeatureConfiguration> REDWOOD_TREE = new RedwoodTreeFeature();
    public static final Feature<NoneFeatureConfiguration> LARGE_REDWOOD_TREE = new LargeRedwoodTreeFeature();
    public static final Feature<NoiseSampleFeatureConfiguration> NOISE_SAMPLE = new NoiseSampleFeature();
    public static final Feature<ShrubFeatureConfiguration> SHRUB = new ShrubFeature();

    public static void register() {
        register("baobab_tree", BAOBAB_TREE);
        register("patagonian_oak_tree", PATAGONIAN_OAK_TREE);
        register("redwood_tree", REDWOOD_TREE);
        register("large_redwood_tree", LARGE_REDWOOD_TREE);
        register("noise_sample", NOISE_SAMPLE);
        register("shrub", SHRUB);
    }

    public static void register(String name, Feature<? extends FeatureConfiguration> feature) {
        Registry.register(BuiltInRegistries.FEATURE, BiomeVariety.resourceLocation(name), feature);
    }

}
