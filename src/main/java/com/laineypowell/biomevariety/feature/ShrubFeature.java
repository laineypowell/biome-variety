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
        switch (random.nextInt(3)) {
            default -> {
                for (var direction : new Direction[] {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP}) {
                    var normal = direction.getNormal();

                    structure.add(normal.getX(), normal.getY(), normal.getZ(), leaves, random);
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
