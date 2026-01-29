package com.laineypowell.biomevariety;

import com.laineypowell.biomevariety.block.BaobabLogWedgeBlock;
import com.laineypowell.biomevariety.block.ButtonweedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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

    public static final Block STRIPPED_BAOBAB_LOG_WEDGE = new BaobabLogWedgeBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block BAOBAB_LOG_WEDGE = new BaobabLogWedgeBlock(Properties.copy(Blocks.OAK_PLANKS)) {
        @Override
        public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
            return BiomeVariety.stripInteraction(player, interactionHand, level, blockPos, blockState, STRIPPED_BAOBAB_LOG_WEDGE);
        }
    };

    public static final Block PATAGONIAN_OAK_LOG = new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG));
    public static final Block PATAGONIAN_OAK_WOOD = new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG));

    public static final Block SILT_GRASS_BLOCK = new Block(Properties.copy(Blocks.DIRT));
    public static final Block SILT = new Block(Properties.copy(Blocks.DIRT));
    public static final Block SILT_PATH = new DirtPathBlock(Properties.copy(Blocks.DIRT_PATH));
    public static final Block SILT_FARM = new FarmBlock(Properties.copy(Blocks.FARMLAND));

    public static final Block SNOWY_ANTARCTIC_ICE = new Block(Properties.of().sound(SoundType.SNOW).randomTicks());
    public static final Block ANTARCTIC_ICE = new Block(Properties.copy(Blocks.BLUE_ICE));
    public static final Block ANTARCTIC_ICE_PATH = new DirtPathBlock(Properties.copy(Blocks.BLUE_ICE));
    public static final Block ANTARCTIC_ICE_FARMLAND = new Block(Properties.copy(Blocks.BLUE_ICE));

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

        register("silt_grass_block", SILT_GRASS_BLOCK);
        register("silt", SILT);
        register("silt_path", SILT_PATH);
        register("silt_farm", SILT_FARM);

        register("snowy_antarctic_ice", SNOWY_ANTARCTIC_ICE);
        register("antarctic_ice", ANTARCTIC_ICE);
        register("antarctic_ice_path", ANTARCTIC_ICE_PATH);
        register("antarctic_ice_farmland", ANTARCTIC_ICE_FARMLAND);

        register("buttonweed", BUTTONWEED);
        register("violet", VIOLET);

    }

    public static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, BiomeVariety.resourceLocation(name), block);
    }
}
