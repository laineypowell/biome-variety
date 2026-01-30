package com.laineypowell.biomevariety.worldgen;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public final class BiomeVarietySurfaceRules {

    public static SurfaceRules.RuleSource ruleSource() {

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeVarietyBiomes.BAOBAB_PLAINS), surface(SurfaceRules.state(Blocks.SAND.defaultBlockState()), SurfaceRules.state(Blocks.SANDSTONE.defaultBlockState()))),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeVarietyBiomes.ANTARCTIC), SurfaceRules.sequence(
                        noise(Noises.POWDER_SNOW, 0.25d,
                                surface(SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState()), SurfaceRules.state(Blocks.DIRT.defaultBlockState())),
                                surface(SurfaceRules.state(BiomeVarietyBlocks.SNOWY_ANTARCTIC_ICE.defaultBlockState()), SurfaceRules.state(BiomeVarietyBlocks.ANTARCTIC_ICE.defaultBlockState())))
                )),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeVarietyBiomes.REDWOOD_FOREST), surface(SurfaceRules.state(BiomeVarietyBlocks.GRASSY_WEATHERED_DIRT.defaultBlockState()), SurfaceRules.state(BiomeVarietyBlocks.WEATHERED_DIRT.defaultBlockState())))
        );
    }

    public static SurfaceRules.RuleSource surface(SurfaceRules.RuleSource onFloor, SurfaceRules.RuleSource underFloor) {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 1), onFloor)),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, underFloor)
        );
    }

    public static SurfaceRules.RuleSource noise(ResourceKey<NormalNoise.NoiseParameters> noise, double d, SurfaceRules.RuleSource left, SurfaceRules.RuleSource right) {
        var condition = SurfaceRules.noiseCondition(noise, d);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(condition, left),
                SurfaceRules.ifTrue(SurfaceRules.not(condition), right)
        );
    }

}
