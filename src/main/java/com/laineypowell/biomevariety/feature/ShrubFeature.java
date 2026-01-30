package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.Structure;
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
            case 1 -> {

            }
            case 2 -> {

            }

            default -> {
                for (var direction : new Direction[] {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP}) {
                    var normal = direction.getNormal();

                    structure.add(normal.getX(), normal.getY(), normal.getZ(), leaves, random);
                }

            }
        }

        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }
}
