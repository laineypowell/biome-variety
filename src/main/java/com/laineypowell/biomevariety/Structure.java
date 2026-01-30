package com.laineypowell.biomevariety;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.objects.ObjectIterators;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Structure {
    private final List<BlockState> blockStates;

    private final Int2ObjectMap<LongList> lists;
    private final Int2IntMap blocks;

    private Structure(List<BlockState> blockStates, Int2ObjectMap<LongList> lists, Int2IntMap blocks) {
        this.blockStates = blockStates;
        this.lists = lists;
        this.blocks = blocks;
    }

    public void add(int x, int y, int z, BlockStateProvider provider, RandomSource randomSource) {
        var blockPos = new BlockPos(x, y, z);
        add(blockPos, provider.getState(randomSource, blockPos));
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

    public Iterator<BlockPos> iterator(BlockState blockState) {
        var i = blockStates.indexOf(blockState);
        if (lists.containsKey(i)) {
            var iterator = lists.get(i).longIterator();
            var blockPos = new BlockPos.MutableBlockPos();
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public BlockPos next() {
                    var l = iterator.nextLong();
                    var x = (int) (l & 0x1FFFFFF);
                    var y = (int) ((l >> 21) & 0x1FFFFFF);
                    var z = (int) ((l >> 42) & 0x1FFFFFF);
                    return blockPos.set(x, y, z);
                }
            };
        }

        return ObjectIterators.emptyIterator();
    }

}
