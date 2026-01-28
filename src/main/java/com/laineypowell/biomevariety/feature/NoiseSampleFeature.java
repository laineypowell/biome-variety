package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.FastNoise;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public final class NoiseSampleFeature extends Feature<NoiseSampleFeatureConfiguration> {

    public NoiseSampleFeature() {
        super(NoiseSampleFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoiseSampleFeatureConfiguration> featurePlaceContext) {
        var config = featurePlaceContext.config();
        var level = featurePlaceContext.level();
        var fastNoise = new FastNoise((int) level.getSeed());

        var r = config.radius();
        var resolution = config.resolution();
        var grain = config.grain();
        for (var x = -r; x <= r + 1; x++) {
            for (var y = -r; y <= r + 1; y++) {
                for (var z = -r; z <= r + 1; z++) {
                    var blockPos = featurePlaceContext.origin().offset(
                            Math.round(x),
                            Math.round(y),
                            Math.round(z)
                    );

                    var i = (blockPos.getX() + resolution) / grain;
                    var j = (blockPos.getY() + resolution) / grain;
                    var k = (blockPos.getZ() + resolution) / grain;
                    var n = fastNoise.GetNoise(i, j, k);

                    if (canReplace(level.getBlockState(blockPos)) && x * x + y * y + z * z <= r * r && n <= config.threshold()) {
                        var above = blockPos.above();
                        var surface = !Block.isShapeFullBlock(level.getBlockState(above).getShape(level, above));

                        level.setBlock(blockPos, (surface? config.blockState() : config.blockStateBelow()).getState(featurePlaceContext.random(), blockPos), Block.UPDATE_ALL);
                    }
                }
            }
        }

        return true;
    }

    public boolean canReplace(BlockState blockState) {
        return !blockState.isAir() && blockState.getFluidState().isEmpty() && blockState.is(BlockTags.OVERWORLD_CARVER_REPLACEABLES);
    }

}
