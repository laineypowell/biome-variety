package com.laineypowell.biomevariety.block;

import com.laineypowell.biomevariety.Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Map;

public final class LeavesAwningBlock extends Block {
    public static final Map<Direction, BooleanProperty> MAP = Map.of(
            Direction.NORTH, BlockStateProperties.NORTH,
            Direction.EAST, BlockStateProperties.EAST,
            Direction.SOUTH, BlockStateProperties.SOUTH,
            Direction.WEST, BlockStateProperties.WEST
    );

    public LeavesAwningBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.NORTH, false).setValue(BlockStateProperties.EAST, false).setValue(BlockStateProperties.SOUTH, false).setValue(BlockStateProperties.WEST, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.NORTH);
        builder.add(BlockStateProperties.EAST);
        builder.add(BlockStateProperties.SOUTH);
        builder.add(BlockStateProperties.WEST);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return canSurvive(blockState, levelAccessor, blockPos) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        var hasFace = false;
        for (var direction : Direction.Plane.HORIZONTAL) {
            if (blockState.getValue(MAP.get(direction)) && isLeaves(levelReader.getBlockState(blockPos.relative(direction.getOpposite())))) {
                hasFace = true;
                break;
            }
        }
        return hasFace;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        var direction = LogBranchBlock.horizontal(blockPlaceContext);
        var property = MAP.get(direction);

        return defaultBlockState().setValue(property, true);
    }

    public BlockState blockState(Structure structure, BlockPos blockPos) {
        var blockState = defaultBlockState();
        for (var direction : Direction.Plane.HORIZONTAL) {
            var l = blockPos.relative(direction.getOpposite()).asLong();

            if (isLeaves(structure.get(l))) {
                blockState = blockState.setValue(MAP.get(direction), true);
            }
        }

        return blockState;
    }

    public boolean isLeaves(BlockState blockState) {
        return blockState.is(BlockTags.LEAVES);
    }

}
