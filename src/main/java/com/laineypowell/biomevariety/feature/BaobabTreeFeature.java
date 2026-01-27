package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.Structure;
import com.laineypowell.biomevariety.block.BaobabLogWedgeBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class BaobabTreeFeature extends Feature<NoneFeatureConfiguration> {
    public BaobabTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        var log = BiomeVarietyBlocks.BAOBAB_LOG.defaultBlockState();
        var wedge = BiomeVarietyBlocks.BAOBAB_LOG_WEDGE.defaultBlockState();

        var structure = Structure.structure();

        var random = featurePlaceContext.random();
        var j = random.nextInt(50) == 0 ? 5 : 6 + random.nextInt(4);
        for (var i = 0; i < j; i++) {
            var b = i < j - 3;
            structure.add(0, i, 0, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.WEST) : log);
            structure.add(1, i, 0, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.SOUTH) : log);
            structure.add(1, i, 1, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.EAST) : log);
            structure.add(0, i, 1, b ? wedge : log);
        }

        for (var i = 0; i < 2; i++) {
            var x = random.nextInt(4) - random.nextInt(3);
            var y = j + random.nextInt(3);
            var z = random.nextInt(4) - random.nextInt(3);

            branch(random.nextInt(1), j, random.nextInt(1), x, y, z, structure, log);
        }

        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }

    public void branch(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Structure structure, BlockState blockState) {
        structure.add(minX, minY, minZ, blockState);
        structure.add(maxX, maxY, maxZ, blockState);
        for (var x = Math.min(minX, maxX); x < Math.max(minX, maxX); x++) {
            for (var y = Math.min(minY, maxY); y < Math.max(minY, maxY); y++) {
                for (var z = Math.min(minZ, maxZ); z < Math.max(minZ, maxZ); z++) {
                    structure.add(x, y, z, blockState);
                }
            }
        }

        var r = 3;
        for (var x = -r; x < r; x++) {
            for (var y = 0; y < 2; y++) {
                for (var z = -r; z < r; z++) {

                    if (true) {
                        structure.add(maxX + x, maxY + y, maxZ + z, Blocks.OAK_LEAVES.defaultBlockState());
                    }

                }
            }
        }
    }

}
