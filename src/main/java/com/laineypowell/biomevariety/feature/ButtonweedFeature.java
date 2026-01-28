package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.block.ButtonweedBlock;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public final class ButtonweedFeature extends Feature<NoneFeatureConfiguration> {
    public ButtonweedFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {

        var d = Util.getRandom(List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST), featurePlaceContext.random());

        featurePlaceContext.level().setBlock(featurePlaceContext.origin(), BiomeVarietyBlocks.BUTTONWEED.defaultBlockState().setValue(ButtonweedBlock.FACING, d), Block.UPDATE_ALL);

        return true;
    }
}
