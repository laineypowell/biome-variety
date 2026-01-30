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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogBranchBlock extends HorizontalDirectionalBlock {
    public static final VoxelShape SHAPE = Shapes.box(0.1875, 0.1875, 0.5, 0.8125, 0.8125, 1);

    private final Map<BlockState, VoxelShape> shapes = getStateDefinition().getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), this::rotateShape));

    public LogBranchBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @SuppressWarnings("all")
    @Override
    public @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return shapes.get(blockState);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(FACING, horizontal(blockPlaceContext));
    }

    public VoxelShape rotateShape(BlockState blockState) {
        return rotateShape(blockState, SHAPE);
    }

    public static VoxelShape rotateShape(BlockState blockState, VoxelShape shape) {
        return BiomeVariety.rotateShape(shape, new Quaternionf().rotateAxis((float) Math.toRadians(ordinal(blockState.getValue(FACING)) * 90.0f), 0.0f, 1.0f, 0.0f));
    }

    public static int ordinal(Direction direction) {
        return switch (direction) {
            case NORTH -> 0;
            case EAST -> 3;
            case SOUTH -> 2;
            default -> 1;
        };
    }

    public static Direction horizontal(BlockPlaceContext context) {
        var direction = context.getClickedFace();
        return Direction.Plane.VERTICAL.test(direction) ? context.getHorizontalDirection(): direction;
    }

}
