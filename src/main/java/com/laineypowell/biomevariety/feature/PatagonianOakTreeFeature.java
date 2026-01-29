package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.Structure;
import com.laineypowell.biomevariety.block.LogBranchBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class PatagonianOakTreeFeature extends Feature<NoneFeatureConfiguration> {
    public PatagonianOakTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        var structure = Structure.structure();

        var log = BiomeVarietyBlocks.PATAGONIAN_OAK_LOG.defaultBlockState();
        var leaves = Blocks.OAK_LEAVES.defaultBlockState();

        structure.add(0, -1, 0, Blocks.DIRT.defaultBlockState());

        var random = featurePlaceContext.random();
        var j = 6 + random.nextInt(3);

        for (var i = 0; i <= j; i++) {
            structure.add(0, i, 0, log);

            if (i >= 3) {
                for (var direction : Direction.Plane.HORIZONTAL) {
                    var normal = direction.getNormal();
                    var x = normal.getX();
                    var z = normal.getZ();

                    if (random.nextInt(2) == 0) {
                        structure.add(x, i, z, leaves);
                    }

                    if (random.nextInt(12) == 0 && i <= j - 1) {
                        structure.add(x, i, z, BiomeVarietyBlocks.PATAGONIAN_OAK_LOG_BRANCH.defaultBlockState().setValue(LogBranchBlock.FACING, direction));
                    }
                }
            }
        }

        structure.add(0, j + 1, 0, leaves);

        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }
}
