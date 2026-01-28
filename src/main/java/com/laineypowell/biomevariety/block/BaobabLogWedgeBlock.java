package com.laineypowell.biomevariety.block;

import com.laineypowell.biomevariety.BiomeVariety;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaobabLogWedgeBlock extends HorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = Shapes.or(Shapes.create(0, 0, 0, 0.25, 1, 1), Shapes.create(0.25, 0, 0.75, 1, 1, 1));

    private final Map<BlockState, VoxelShape> shapes;

    public BaobabLogWedgeBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));

        shapes = getStateDefinition().getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), BaobabLogWedgeBlock::voxelShape));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return shapes.get(blockState);
    }

    public static VoxelShape voxelShape(BlockState blockState) {
        return BiomeVariety.rotate(SHAPE, new Quaternionf().rotateAxis((float) Math.toRadians(blockState.getValue(FACING).getOpposite().toYRot()), 0.0f, 1.0f, 0.0f));
    }

}
