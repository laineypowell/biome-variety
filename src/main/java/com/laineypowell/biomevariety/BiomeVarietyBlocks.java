package com.laineypowell.biomevariety;

import com.laineypowell.biomevariety.block.BaobabLogWedgeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public final class BiomeVarietyBlocks {
    public static final Block BAOBAB_LOG = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block BAOBAB_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block STRIPPED_BAOBAB_LOG = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block STRIPPED_BAOBAB_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.OAK_PLANKS));

    public static final Block STRIPPED_BAOBAB_LOG_WEDGE = new BaobabLogWedgeBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block BAOBAB_LOG_WEDGE = new BaobabLogWedgeBlock(Properties.copy(Blocks.OAK_PLANKS)) {
        @Override
        public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
            var itemStack = player.getItemInHand(interactionHand);
            if (itemStack.getItem() instanceof AxeItem) {
                var client = level.isClientSide;
                if (!client) {
                    level.setBlock(blockPos, STRIPPED_BAOBAB_LOG_WEDGE.defaultBlockState().setValue(BaobabLogWedgeBlock.FACING, blockState.getValue(BaobabLogWedgeBlock.FACING)), 11);
                } else {
                    level.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);
                }
                return InteractionResult.sidedSuccess(client);

            }

            return InteractionResult.PASS;
        }
    };

    public static void register() {
        register("baobab_log", BAOBAB_LOG);
        register("baobab_wood", BAOBAB_WOOD);
        register("stripped_baobab_log", STRIPPED_BAOBAB_LOG);
        register("stripped_baobab_wood", STRIPPED_BAOBAB_WOOD);
        register("baobab_log_wedge", BAOBAB_LOG_WEDGE);
        register("stripped_baobab_log_wedge", STRIPPED_BAOBAB_LOG_WEDGE);
    }

    public static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, BiomeVariety.resourceLocation(name), block);
    }
}
