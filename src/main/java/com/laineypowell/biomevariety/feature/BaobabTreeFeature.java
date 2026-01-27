package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.Structure;
import com.laineypowell.biomevariety.block.BaobabLogWedgeBlock;
import net.minecraft.core.Direction;
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
            var b = i < j - 2;
            structure.add(0, i, 0, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.WEST) : log);
            structure.add(1, i, 0, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.SOUTH) : log);
            structure.add(1, i, 1, b ? wedge.setValue(BaobabLogWedgeBlock.FACING, Direction.EAST) : log);
            structure.add(0, i, 1, b ? wedge : log);
        }

        for (var i = 0; i < 2; i++) {
            structure.add(0, j, 0, log);
        }

        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }

}
