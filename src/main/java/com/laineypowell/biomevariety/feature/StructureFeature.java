package com.laineypowell.biomevariety.feature;

import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

public final class StructureFeature extends Feature<StructureFeatureConfiguration> {

    public StructureFeature() {
        super(StructureFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<StructureFeatureConfiguration> featurePlaceContext) {
        var random = featurePlaceContext.random();
        var rotation = Rotation.getRandom(random);

        var level = featurePlaceContext.level();

        var name = featurePlaceContext.config().structures().get(random);
        var template = level.getLevel().getStructureManager().get(name).orElseThrow(() -> new RuntimeException(String.format("No value present %s", name)));

        var size = template.getSize(rotation);
        var blockPos = featurePlaceContext.origin().offset(size.getX() / 2, 0, size.getZ() / 2);
        template.placeInWorld(level, blockPos, template.getZeroPositionWithTransform(blockPos, Mirror.NONE, rotation), new StructurePlaceSettings().setRandom(random).setRotation(rotation), random, 0);
        return true;
    }
}
