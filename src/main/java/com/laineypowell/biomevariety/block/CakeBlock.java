package com.laineypowell.biomevariety.block;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public final class CakeBlock extends Block {
    public static final EnumProperty<CoveredWith> COVERED_WITH = EnumProperty.create("covered_with", CoveredWith.class);

    public CakeBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(COVERED_WITH, CoveredWith.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COVERED_WITH);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        var above = levelAccessor.getBlockState(blockPos.above());
        return blockState.setValue(COVERED_WITH, above.is(BlockTags.SNOW) ? CoveredWith.SNOW : above.is(BiomeVarietyBlocks.ICING_LAYER) ? CoveredWith.ICING : CoveredWith.NONE);
    }

    public enum CoveredWith implements StringRepresentable {
        NONE("none"),
        ICING("icing"),
        SNOW("snow");

        private final String name;

        CoveredWith(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
