package com.laineypowell.biomevariety.feature;

import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public final class FlowerFeature extends Feature<NoneFeatureConfiguration> {
    public FlowerFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {

        var d = Util.getRandom(List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST), featurePlaceContext.random());

        featurePlaceContext.level().setBlock(featurePlaceContext.origin(), Blocks.PINK_PETALS.defaultBlockState().setValue(PinkPetalsBlock.FACING, d), Block.UPDATE_ALL);

        return true;
    }
}
