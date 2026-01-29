package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.FastNoise;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
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
        for (var x = -r; x <= r + 1; x++) {
            for (var y = -r; y <= r + 1; y++) {
                for (var z = -r; z <= r + 1; z++) {
                    var blockPos = featurePlaceContext.origin().offset(
                            Math.round(x),
                            Math.round(y),
                            Math.round(z)
                    );

                    var n = config.noise().sample(blockPos, featurePlaceContext.random(), fastNoise);

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

}
