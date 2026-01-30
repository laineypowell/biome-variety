package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.Structure;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class RedwoodTreeFeature extends Feature<NoneFeatureConfiguration> {
    public RedwoodTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        var structure = Structure.structure();

        for (var i = 0; i <= 6 + featurePlaceContext.random().nextInt(4); i++) {
            structure.add(0, i, 0, BiomeVarietyBlocks.REDWOOD_LOG.defaultBlockState());
        }

        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }
}
