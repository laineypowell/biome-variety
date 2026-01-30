package com.laineypowell.biomevariety.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class LogRootBlock extends LogBranchBlock {
    public LogRootBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(Side.SIDE, Side.LEFT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(Side.SIDE));
    }
}
