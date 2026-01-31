package com.laineypowell.biomevariety;

import com.laineypowell.biomevariety.worldgen.BiomeVarietyFeatures;
import com.laineypowell.biomevariety.worldgen.BiomeVarietyRegion;
import com.laineypowell.biomevariety.worldgen.BiomeVarietySurfaceRules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public final class BiomeVariety implements ModInitializer, TerraBlenderApi {
    public static final String MOD_ID = "biome-variety";

    @Override
    public void onInitialize() {
        BiomeVarietyBlocks.register();
        BiomeVarietyItems.register();
        BiomeVarietyFeatures.register();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceLocation(MOD_ID), FabricItemGroup.builder()
                        .icon(BiomeVarietyItems.BAOBAB_LOG::getDefaultInstance)
                        .title(Component.translatable("itemGroup.biome-variety"))
                        .displayItems((itemDisplayParameters, output) -> {
                            var provider = itemDisplayParameters.holders();
                            accept(provider, BiomeVarietyItemTags.BAOBAB_LOGS, output);
                            accept(provider, BiomeVarietyItemTags.PATAGONIAN_OAK_LOGS, output);
                            accept(provider, BiomeVarietyItemTags.REDWOOD_LOGS, output);
                            output.accept(BiomeVarietyItems.SHRUB_LEAVES);
                            output.accept(BiomeVarietyItems.SHRUB_LEAVES_AWNING);
                            accept(provider, BiomeVarietyItemTags.DUNE_SAND, output);
                            accept(provider, BiomeVarietyItemTags.ANTARCTIC_ICE, output);
                            accept(provider, BiomeVarietyItemTags.WEATHERED_DIRT, output);
                            accept(provider, BiomeVarietyItemTags.CAKE_BLOCK, output);
                            output.accept(BiomeVarietyItems.MUFFIN);
                            output.accept(BiomeVarietyItems.GIANT_SWEET_BERRY);
                            output.accept(BiomeVarietyItems.JAM_FILLING);
                            output.accept(BiomeVarietyItems.ICING_LAYER);

                            output.accept(BiomeVarietyItems.SAVANNA_GRASS);
                            output.accept(BiomeVarietyItems.FOREST_FERN);
                            output.accept(BiomeVarietyItems.DRY_LEAVES);
                            output.accept(BiomeVarietyItems.BUTTONWEED);
                            output.accept(BiomeVarietyItems.VIOLET);
                        })
                .build());

        log(BiomeVarietyBlocks.BAOBAB_LOG, BiomeVarietyBlocks.STRIPPED_BAOBAB_LOG);
        log(BiomeVarietyBlocks.BAOBAB_WOOD, BiomeVarietyBlocks.STRIPPED_BAOBAB_WOOD);
        log(BiomeVarietyBlocks.PATAGONIAN_OAK_LOG, BiomeVarietyBlocks.STRIPPED_PATAGONIAN_OAK_LOG);
        log(BiomeVarietyBlocks.PATAGONIAN_OAK_WOOD, BiomeVarietyBlocks.STRIPPED_PATAGONIAN_OAK_WOOD);
        log(BiomeVarietyBlocks.REDWOOD_LOG, BiomeVarietyBlocks.STRIPPED_REDWOOD_LOG);
        log(BiomeVarietyBlocks.REDWOOD_WOOD, BiomeVarietyBlocks.STRIPPED_REDWOOD_WOOD);

        dirt(BiomeVarietyBlocks.GRASSY_DUNE_SAND, BiomeVarietyBlocks.DUNE_SAND, BiomeVarietyBlocks.DUNE_SAND_FARMLAND);
        dirt(BiomeVarietyBlocks.DUNE_SAND, BiomeVarietyBlocks.DUNE_SAND, BiomeVarietyBlocks.DUNE_SAND_FARMLAND);

        dirt(BiomeVarietyBlocks.SNOWY_ANTARCTIC_ICE, BiomeVarietyBlocks.ANTARCTIC_ICE_PATH, BiomeVarietyBlocks.ANTARCTIC_ICE_FARMLAND);
        dirt(BiomeVarietyBlocks.ANTARCTIC_ICE, BiomeVarietyBlocks.ANTARCTIC_ICE_PATH, BiomeVarietyBlocks.ANTARCTIC_ICE_FARMLAND);
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new BiomeVarietyRegion(resourceLocation(MOD_ID), 10));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, BiomeVarietySurfaceRules.ruleSource());
    }

    public void log(Block log, Block strippedLog) {
        StrippableBlockRegistry.register(log, strippedLog);
        var flammableBlocks = FlammableBlockRegistry.getDefaultInstance();
        flammableBlocks.add(log, 5, 5);
        flammableBlocks.add(strippedLog, 5, 5);
    }

    public void dirt(Block block, Block pathBlock, Block farmBlock) {
        FlattenableBlockRegistry.register(block, pathBlock.defaultBlockState());
        TillableBlockRegistry.register(block, useOnContext -> true, farmBlock.defaultBlockState());
    }

    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static VoxelShape rotateShape(VoxelShape shape, Quaternionf quaternionf) {
        var matrix4f = new Matrix4f();
        matrix4f.translate(0.5f, 0.5f, 0.5f);
        matrix4f.rotate(quaternionf);
        matrix4f.translate(-0.5f, -0.5f, -0.5f);

        var rotatedShape = Shapes.empty();

        var min = new Vector3f();
        var max = new Vector3f();
        for (var aabb : shape.toAabbs()) {
            matrix4f.transformAab((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ, min, max);
            rotatedShape = Shapes.or(rotatedShape, Shapes.create(min.x, min.y, min.z, max.x, max.y, max.z));
        }

        return rotatedShape;
    }

    @SuppressWarnings("unchecked")
    public static InteractionResult stripInteraction(Player player, InteractionHand hand, Level level, BlockPos blockPos, BlockState blockState, Block stripped) {
        if (player != null) {
            var itemStack = player.getItemInHand(hand);
            if (itemStack.is(ItemTags.AXES)) {
                var clientSided = level.isClientSide;
                if (clientSided) {
                    level.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);
                } else {
                    var result = stripped.defaultBlockState();
                    for (var entry : blockState.getValues().entrySet()) {
                        result = result.setValue((Property) entry.getKey(), (Comparable) entry.getValue());
                    }

                    if (level.setBlock(blockPos, result, Block.UPDATE_ALL_IMMEDIATE)) {
                        itemStack.hurtAndBreak(1, player, thePlayer -> thePlayer.broadcastBreakEvent(hand));
                    }
                }

                return InteractionResult.sidedSuccess(clientSided);
            }
        }

        return InteractionResult.PASS;
    }

    public static void accept(HolderLookup.Provider provider, TagKey<Item> tagKey, CreativeModeTab.Output output) {
        for (var holder : provider.lookupOrThrow(tagKey.registry()).getOrThrow(tagKey)) {
            output.accept(holder.value());
        }
    }

}
