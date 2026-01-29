package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.FastNoise;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.levelgen.Heightmap;
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

                    var i = (blockPos.getX() / 2.0f) * config.grain();
                    var j = (blockPos.getY() / 2.0f) * config.grain();
                    var k = (blockPos.getZ() / 2.0f) * config.grain();
                    var n = fastNoise.GetNoise(i, j, k);

                    if (!level.isEmptyBlock(blockPos) && config.predicate().test(level, blockPos) && x * x + y * y + z * z <= r * r && n >= config.threshold()) {
                        var worldSurface = level.getBlockState(blockPos.above());

                        var surface = worldSurface.isAir() || worldSurface.getBlock() instanceof BushBlock;

                        var pair = config.surface().getBlockStateProviders();
                        level.setBlock(blockPos, (surface? pair.left() : pair.right()).getState(featurePlaceContext.random(), blockPos), Block.UPDATE_ALL);
                    }
                }
            }
        }

        return true;
    }

    public BlockPos worldSurface(BlockPos blockPos, WorldGenLevel level) {
        return blockPos.atY(level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockPos.getX(), blockPos.getZ()));
    }

}
