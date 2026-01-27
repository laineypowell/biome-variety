package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class BaobabTreeFeature extends Feature<NoneFeatureConfiguration> {
    public BaobabTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {

        var level = featurePlaceContext.level();
        var blockPos = featurePlaceContext.origin();
        var blockState = BiomeVarietyBlocks.BAOBAB_LOG.defaultBlockState();

        var random = featurePlaceContext.random();
        var j = random.nextInt(50) == 0 ? 5 : 6 + random.nextInt(4);
        for (var i = 0; i < j; i++) {
            level.setBlock(blockPos.above(i), blockState, Block.UPDATE_ALL);
            level.setBlock(blockPos.above(i).offset(1, 0, 0), blockState, Block.UPDATE_ALL);
            level.setBlock(blockPos.above(i).offset(0, 0, 1), blockState, Block.UPDATE_ALL);
            level.setBlock(blockPos.above(i).offset(1, 0, 1), blockState, Block.UPDATE_ALL);
        }

        for (var i = 0; i < 2; i++) {
            level.setBlock(blockPos.above(j), blockState, Block.UPDATE_ALL);
        }

        return true;
    }

}
