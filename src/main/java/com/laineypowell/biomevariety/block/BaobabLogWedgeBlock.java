package com.laineypowell.biomevariety.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class BaobabLogWedgeBlock extends LogBranchBlock {
    public static final VoxelShape SHAPE = Shapes.or(Shapes.create(0, 0, 0, 0.25, 1, 1), Shapes.create(0.25, 0, 0.75, 1, 1, 1));

    public BaobabLogWedgeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        var clickedFace = blockPlaceContext.getClickedFace();
        var map = Map.of(
                Direction.SOUTH, Direction.EAST,
                Direction.WEST, Direction.WEST,
                Direction.EAST, Direction.EAST,
                Direction.NORTH, Direction.WEST);

        return defaultBlockState().setValue(FACING, clickedFace == Direction.DOWN || clickedFace == Direction.UP ? blockPlaceContext.getHorizontalDirection().getOpposite() : map.get(clickedFace));
    }

    @Override
    public VoxelShape rotateShape(BlockState blockState) {
        return LogBranchBlock.rotateShape(blockState, SHAPE);
    }

}
