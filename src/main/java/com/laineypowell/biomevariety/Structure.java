package com.laineypowell.biomevariety;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public final class Structure {
    private final Map<BlockPos, BlockState> map;

    private Structure(Map<BlockPos, BlockState> map) {
        this.map = map;
    }

    public void add(int x, int y, int z, BlockState blockState) {
        add(new BlockPos(x, y, z), blockState);
    }

    public void add(BlockPos blockPos, BlockState blockState) {
        if (map.containsKey(blockPos) && map.get(blockPos).is(BlockTags.LEAVES) && !blockState.is(BlockTags.LEAVES)) {
            map.put(blockPos, blockState);
            return;
        }

        map.putIfAbsent(blockPos, blockState);
    }

    public Structure rotate(RandomSource randomSource) {
        return rotate(Rotation.getRandom(randomSource));
    }

    public Structure rotate(Rotation rotation) {
        Map<BlockPos, BlockState> map = new HashMap<>();
        for (var entry : this.map.entrySet()) {
            map.put(entry.getKey().rotate(rotation), entry.getValue().rotate(rotation));
        }

        return new Structure(map);
    }

    public void place(WorldGenLevel level, BlockPos blockPos) {
        for (var entry : map.entrySet()) {
            setBlock(level, entry.getKey().offset(blockPos), entry.getValue());
        }

    }

    private void setBlock(WorldGenLevel level, BlockPos blockPos, BlockState blockState) {
        var replacing = level.getBlockState(blockPos);
        if (replacing.is(BlockTags.REPLACEABLE_BY_TREES) || replacing.is(BlockTags.REPLACEABLE)) {
            level.setBlock(blockPos, blockState, Block.UPDATE_ALL);
            level.scheduleTick(blockPos, blockState.getBlock(), 1);
        }
    }

    public static Structure structure() {
        return new Structure(new HashMap<>());
    }

}
