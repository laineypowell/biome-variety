package com.laineypowell.biomevariety;

import com.laineypowell.biomevariety.block.BaobabLogWedgeBlock;
import com.laineypowell.biomevariety.block.ButtonweedBlock;
import com.laineypowell.biomevariety.block.DryLeavesBlock;
import com.laineypowell.biomevariety.block.LogBranchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public final class BiomeVarietyBlocks {
    public static final Block BAOBAB_LOG = new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG));
    public static final Block BAOBAB_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.OAK_WOOD));
    public static final Block STRIPPED_BAOBAB_LOG = new RotatedPillarBlock(Properties.copy(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_BAOBAB_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.STRIPPED_OAK_WOOD));

    public static final Block BAOBAB_LOG_WEDGE = new BaobabLogWedgeBlock(Properties.copy(Blocks.OAK_PLANKS)) {
        @Override
        public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
            return BiomeVariety.stripInteraction(player, interactionHand, level, blockPos, blockState, STRIPPED_BAOBAB_LOG_WEDGE);
        }
    };
    public static final Block STRIPPED_BAOBAB_LOG_WEDGE = new BaobabLogWedgeBlock(Properties.copy(Blocks.OAK_PLANKS));

    public static final Block PATAGONIAN_OAK_LOG = new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG));
    public static final Block PATAGONIAN_OAK_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG));

    public static final Block PATAGONIAN_OAK_LOG_BRANCH = new LogBranchBlock(Properties.copy(Blocks.OAK_PLANKS)) {
        @Override
        public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
            return BiomeVariety.stripInteraction(player, interactionHand, level, blockPos, blockState, STRIPPED_PATAGONIAN_OAK_LOG_BRANCH);
        }
    };
    public static final Block STRIPPED_PATAGONIAN_OAK_LOG_BRANCH = new LogBranchBlock(Properties.copy(Blocks.OAK_PLANKS));

    public static final Block GRASSY_DUNE_SAND = new Block(Properties.copy(Blocks.DIRT));
    public static final Block DUNE_SAND = new Block(Properties.copy(Blocks.DIRT));
    public static final Block DUNE_SAND_PATH = new DirtPathBlock(Properties.copy(Blocks.DIRT_PATH));
    public static final Block DUNE_SAND_FARMLAND = new FarmBlock(Properties.copy(Blocks.FARMLAND));

    public static final Block SNOWY_ANTARCTIC_ICE = new Block(Properties.copy(Blocks.PACKED_ICE).friction(0.6f).sound(SoundType.SNOW).randomTicks());
    public static final Block ANTARCTIC_ICE = new Block(Properties.copy(Blocks.PACKED_ICE));
    public static final Block ANTARCTIC_ICE_PATH = new DirtPathBlock(Properties.copy(Blocks.PACKED_ICE)) {
        @Override
        public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
            serverLevel.setBlockAndUpdate(blockPos, ANTARCTIC_ICE.defaultBlockState());
        }
    };
    public static final Block ANTARCTIC_ICE_FARMLAND = new Block(Properties.copy(Blocks.BLUE_ICE));

    public static final Block SAVANNA_GRASS = new DoublePlantBlock(Properties.copy(Blocks.TALL_GRASS)) {
        @Override
        protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
            return super.mayPlaceOn(blockState, blockGetter, blockPos) || blockState.is(BlockTags.SAND);
        }
    };
    public static final Block DRY_LEAVES = new DryLeavesBlock(Properties.of().sound(SoundType.CHERRY_LEAVES).instabreak().noCollission().noOcclusion().noLootTable());
    public static final Block BUTTONWEED = new ButtonweedBlock(Properties.of().sound(SoundType.PINK_PETALS).instabreak().noCollission().noOcclusion());
    public static final Block VIOLET = new FlowerBlock(MobEffects.BLINDNESS, 1, Properties.of().sound(SoundType.PINK_PETALS).instabreak().noCollission().noOcclusion().offsetType(BlockBehaviour.OffsetType.XZ)) {

        @Override
        protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
            return super.mayPlaceOn(blockState, blockGetter, blockPos) || blockState.is(Blocks.STONE);
        }
    };

    public static void register() {
        register("baobab_log", BAOBAB_LOG);
        register("baobab_wood", BAOBAB_WOOD);
        register("stripped_baobab_log", STRIPPED_BAOBAB_LOG);
        register("stripped_baobab_wood", STRIPPED_BAOBAB_WOOD);
        register("baobab_log_wedge", BAOBAB_LOG_WEDGE);
        register("stripped_baobab_log_wedge", STRIPPED_BAOBAB_LOG_WEDGE);

        register("patagonian_oak_log", PATAGONIAN_OAK_LOG);
        register("patagonian_oak_wood", PATAGONIAN_OAK_WOOD);
        register("patagonian_oak_log_branch", PATAGONIAN_OAK_LOG_BRANCH);
        register("stripped_patagonian_oak_log_branch", STRIPPED_PATAGONIAN_OAK_LOG_BRANCH);

        register("grassy_dune_sand", GRASSY_DUNE_SAND);
        register("dune_sand", DUNE_SAND);
        register("dune_sand_path", DUNE_SAND_PATH);
        register("dune_sand_farmland", DUNE_SAND_FARMLAND);

        register("snowy_antarctic_ice", SNOWY_ANTARCTIC_ICE);
        register("antarctic_ice", ANTARCTIC_ICE);
        register("antarctic_ice_path", ANTARCTIC_ICE_PATH);
        register("antarctic_ice_farmland", ANTARCTIC_ICE_FARMLAND);

        register("savanna_grass", SAVANNA_GRASS);
        register("dry_leaves", DRY_LEAVES);
        register("buttonweed", BUTTONWEED);
        register("violet", VIOLET);

    }

    public static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, BiomeVariety.resourceLocation(name), block);
    }
}
