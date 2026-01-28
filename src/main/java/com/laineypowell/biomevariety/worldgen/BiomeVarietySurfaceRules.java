package com.laineypowell.biomevariety.worldgen;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public final class BiomeVarietySurfaceRules {

    public static SurfaceRules.RuleSource ruleSource() {
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeVarietyBiomes.BAOBAB_PLAINS), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                        surface(SurfaceRules.state(Blocks.SAND.defaultBlockState())),
                        underground()
                )));
    }

    public static SurfaceRules.RuleSource surface(SurfaceRules.RuleSource ruleSource) {
        return SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 1), SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), ruleSource));
    }

    public static SurfaceRules.RuleSource noise(ResourceKey<NormalNoise.NoiseParameters> noise, double d, BlockState left, BlockState right) {
        var condition = SurfaceRules.noiseCondition(noise, d);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(condition, SurfaceRules.state(left)),
                SurfaceRules.ifTrue(SurfaceRules.not(condition), SurfaceRules.state(right))
        );

    }

    public static SurfaceRules.RuleSource underground() {
        return SurfaceRules.sequence(
                SurfaceRules.state(BiomeVarietyBlocks.SANDY_DIRT.defaultBlockState()),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(Blocks.STONE.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SurfaceRules.state(Blocks.STONE.defaultBlockState()))
        );
    }

}
