package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public final class ShrubFeature extends Feature<ShrubFeatureConfiguration> {
    public ShrubFeature() {
        super(ShrubFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<ShrubFeatureConfiguration> featurePlaceContext) {
        var structure = Structure.structure();

        var config = featurePlaceContext.config();
        var random = featurePlaceContext.random();

        structure.add(0, 0, 0, config.log(), random);

        var leaves = config.leaves();
        var r = 1;
        var l = 2 + random.nextInt(2);
        for (var x = -r; x <= r; x++) {
            for (var y = 0; y <= r; y++) {
                for (var z = -r; z <= r; z++) {
                    if (x * x + y * y + z * z < l) {
                        structure.add(x, y, z, leaves, random);
                    }

                }
            }
        }

        var iterator = structure.iterator(leaves.getState(random, BlockPos.ZERO));
        while (iterator.hasNext()) {
            var next = iterator.next();

            for (var direction : Direction.Plane.HORIZONTAL) {
                var normal = direction.getNormal();
                var x = next.getX() + normal.getX();
                var z = next.getZ() + normal.getZ();
                structure.add(x, next.getY(), z, BiomeVarietyBlocks.SHRUB_LEAVES_AWNING.blockState(structure, next.relative(direction)));
            }
        }
        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }
}
