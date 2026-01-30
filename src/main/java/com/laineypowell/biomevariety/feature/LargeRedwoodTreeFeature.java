package com.laineypowell.biomevariety.feature;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import com.laineypowell.biomevariety.Structure;
import com.laineypowell.biomevariety.block.LogRoot;
import com.laineypowell.biomevariety.block.LogRootBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.joml.Vector4i;

public final class LargeRedwoodTreeFeature extends Feature<NoneFeatureConfiguration> {
    private final Vector4i[] matrix4i = {
            new Vector4i( 0, -1,  1, -1),
            new Vector4i( 1,  2,  0,  2),
            new Vector4i(-1,  1, -1,  0),
            new Vector4i( 2,  0,  2,  1),

    };

    public LargeRedwoodTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        var structure = Structure.structure();

        var dirt = Blocks.DIRT.defaultBlockState();
        structure.add(0, -1, 0, dirt);
        structure.add(1, -1, 0, dirt);
        structure.add(1, -1, 1, dirt);
        structure.add(0, -1, 1, dirt);

        var log = BiomeVarietyBlocks.REDWOOD_LOG.defaultBlockState();
        for (var i = 0; i < 12 + featurePlaceContext.random().nextInt(5); i++) {
            structure.add(0, i, 0, log);
            structure.add(1, i, 0, log);
            structure.add(1, i, 1, log);
            structure.add(0, i, 1, log);
        }

        for (var direction : Direction.Plane.HORIZONTAL) {
            var vector4i = matrix4i[direction.ordinal() - 2];

            var root = BiomeVarietyBlocks.REDWOOD_LOG_ROOT.defaultBlockState().setValue(LogRootBlock.FACING, direction);
            structure.add(vector4i.x, 0, vector4i.y, root.setValue(LogRoot.LOG_ROOT, LogRoot.RIGHT));
            structure.add(vector4i.z, 0, vector4i.w, root);
        }

        structure.place(featurePlaceContext.level(), featurePlaceContext.origin());

        return true;
    }
}
