package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.Structure;
import com.laineypowell.biomevariety.block.BaobabLogWedgeBlock;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
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
        var j = random.nextInt(50) == 0 ? 5 : 6 + random.nextInt(3);

        var dirt = Blocks.DIRT.defaultBlockState();
        structure.add(0, -1, 0, dirt);
        structure.add(1, -1, 0, dirt);
        structure.add(1, -1, 1, dirt);
        structure.add(0, -1, 1, dirt);

        for (var i = 0; i < j; i++) {
            var b = i < j - 3;
            structure.add(0, i, 0, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.EAST) : log);
            structure.add(1, i, 0, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.SOUTH) : log);
            structure.add(1, i, 1, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.WEST) : log);
            structure.add(0, i, 1, b ? wedge : log);
        }

        var r = 4;
        for (var i = 0; i < 3; i++) {
            var x = random.nextInt(r) - random.nextInt(r);
            var z = random.nextInt(r) - random.nextInt(r);

            branch(random.nextInt(1), j, random.nextInt(1), x, j + 1 + random.nextInt(2), z, structure, random);
        }

        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }

    public void branch(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Structure structure, RandomSource randomSource) {
        var blockState = BiomeVarietyBlocks.BAOBAB_LOG.defaultBlockState();
        structure.add(minX, minY, minZ, blockState);
        structure.add(maxX, maxY, maxZ, blockState);

        var dx = maxX - minX;
        var dy = maxY - minY;
        var dz = maxZ - minZ;
        var i = Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz));
        for (var j = 0; j <= i; j++) {
            var f = (float) j / i;
            structure.add(
                    Math.round(minX + dx * f),
                    Math.round(minY + dy * f),
                    Math.round(minZ + dz * f), blockState);
        }

        var r = 3 + (randomSource.nextInt(5) == 0 ? 1 : 0);
        for (var x = -r; x < r + 1; x++) {
            for (var y = 0; y < 2; y++) {
                for (var z = -r; z < r + 1; z++) {

                    var blob = (x * x + y * y + z * z) + 1.5f;
                    if (blob <= r * r + r) {
                        structure.add(maxX + x, maxY + y, maxZ + z, Blocks.OAK_LEAVES.defaultBlockState());
                    }
                }
            }
        }
    }

}
