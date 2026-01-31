package com.laineypowell.biomevariety.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public final class StructureFeature extends Feature<StructureFeatureConfiguration> {
    public StructureFeature() {
        super(StructureFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<StructureFeatureConfiguration> featurePlaceContext) {
        //featurePlaceContext.level().getLevel().getStructureManager().get();

        return false;
    }
}
