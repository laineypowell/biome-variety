package com.laineypowell.biomevariety;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2IntArrayMap;
import it.unimi.dsi.fastutil.longs.Long2IntMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.objects.ObjectIterators;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Structure {
    private final List<BlockState> blockStates;

    private final Int2ObjectMap<LongList> lists;
    private final Long2IntMap blocks;

    private final BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

    private Structure(List<BlockState> blockStates, Int2ObjectMap<LongList> lists, Long2IntMap blocks) {
        this.blockStates = blockStates;
        this.lists = lists;
        this.blocks = blocks;
    }

    public void add(int x, int y, int z, BlockStateProvider provider, RandomSource randomSource) {
        add(x, y, z, provider.getState(randomSource, blockPos.set(x, y, z)));
    }

    public void add(int x, int y, int z, BlockState blockState) {
        var l = BlockPos.asLong(x, y, z);

        if (!blockStates.contains(blockState)) {
            blockStates.add(blockState);
        }

        var i = blockStates.indexOf(blockState);
        lists.computeIfAbsent(i, j -> new LongArrayList()).add(l);

        if (blockState.is(BlockTags.LOGS)) {
            blocks.put(l, i);
        } else {
            blocks.putIfAbsent(l, i);
        }
    }

    public void place(WorldGenLevel level, BlockPos blockPos) {
        for (var entry : blocks.long2IntEntrySet()) {
            var l = entry.getLongKey();
            setBlock(level, blockPos.offset(BlockPos.getX(l), BlockPos.getY(l), BlockPos.getZ(l)), blockStates.get(entry.getIntValue()));
        }
    }

    private void setBlock(WorldGenLevel level, BlockPos blockPos, BlockState blockState) {
        var replacing = level.getBlockState(blockPos);
        if (replacing.is(BlockTags.REPLACEABLE_BY_TREES) || replacing.is(BlockTags.REPLACEABLE)) {
            level.setBlock(blockPos, blockState, Block.UPDATE_ALL);
            level.scheduleTick(blockPos, blockState.getBlock(), 1);
        }
    }

    public BlockState get(long l) {
        return blocks.containsKey(l) ? blockStates.get(blocks.get(l)) : Blocks.AIR.defaultBlockState();
    }

    public Iterator<BlockPos> iterator(BlockState blockState) {
        var i = blockStates.indexOf(blockState);
        if (lists.containsKey(i)) {
            var iterator = lists.get(i).longIterator();
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public BlockPos next() {
                    var l = iterator.nextLong();
                    return blockPos.set(
                            BlockPos.getX(l),
                            BlockPos.getY(l),
                            BlockPos.getZ(l)
                    );
                }
            };
        }

        return ObjectIterators.emptyIterator();
    }

    public static Structure structure() {
        return new Structure(new ArrayList<>(), new Int2ObjectArrayMap<>(), new Long2IntArrayMap());
    }

}
