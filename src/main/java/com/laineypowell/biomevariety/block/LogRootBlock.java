package com.laineypowell.biomevariety.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LogRootBlock extends LogBranchBlock {

    public LogRootBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(LogRoot.LOG_ROOT, LogRoot.LEFT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(LogRoot.LOG_ROOT));
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        var i = blockState.getValue(LogRoot.LOG_ROOT).ordinal();
        var values = LogRoot.values();
        return blockState.setValue(LogRoot.LOG_ROOT, values[levelAccessor.getBlockState(blockPos.above()).is(this) ? i % 2 : (i % 2) + 2]);
    }

    @Override
    public VoxelShape rotateShape(BlockState blockState) {
        return super.rotateShape(blockState);
    }

}
