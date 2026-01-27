package com.laineypowell.biomevariety.worldgen;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public final class BiomeVarietySurfaceRules {

    public static SurfaceRules.RuleSource ruleSource() {
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeVarietyBiomes.BAOBAB_PLAINS), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 1), SurfaceRules.state(Blocks.GRASS.defaultBlockState())),
                        SurfaceRules.state(Blocks.DIRT.defaultBlockState())
                )));
    }
}
