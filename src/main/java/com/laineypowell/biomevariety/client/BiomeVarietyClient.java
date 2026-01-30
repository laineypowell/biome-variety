package com.laineypowell.biomevariety.client;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;

public final class BiomeVarietyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                BiomeVarietyBlocks.SAVANNA_GRASS,
                BiomeVarietyBlocks.DRY_LEAVES,
                BiomeVarietyBlocks.BUTTONWEED,
                BiomeVarietyBlocks.VIOLET,
                BiomeVarietyBlocks.GRASSY_DUNE_SAND,
                BiomeVarietyBlocks.GRASSY_WEATHERED_DIRT,
                BiomeVarietyBlocks.PATAGONIAN_OAK_LOG_BRANCH,
                BiomeVarietyBlocks.STRIPPED_PATAGONIAN_OAK_LOG_BRANCH);

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> grassColour(blockAndTintGetter, blockPos),
                BiomeVarietyBlocks.GRASSY_DUNE_SAND, BiomeVarietyBlocks.GRASSY_WEATHERED_DIRT);
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> foliageColour(blockAndTintGetter, blockPos),
                BiomeVarietyBlocks.DRY_LEAVES,
                BiomeVarietyBlocks.PATAGONIAN_OAK_LOG_BRANCH,
                BiomeVarietyBlocks.STRIPPED_PATAGONIAN_OAK_LOG_BRANCH);

        ColorProviderRegistry.ITEM.register((itemStack, i) -> grassColour(null, null),
                BiomeVarietyBlocks.GRASSY_DUNE_SAND, BiomeVarietyBlocks.GRASSY_WEATHERED_DIRT);
        ColorProviderRegistry.ITEM.register((itemStack, i) -> foliageColour(null, null),
                BiomeVarietyBlocks.DRY_LEAVES,
                BiomeVarietyBlocks.PATAGONIAN_OAK_LOG_BRANCH,
                BiomeVarietyBlocks.STRIPPED_PATAGONIAN_OAK_LOG_BRANCH);
    }

    public int grassColour(BlockAndTintGetter getter, BlockPos blockPos) {
        return getter != null && blockPos != null ? BiomeColors.getAverageGrassColor(getter, blockPos) : GrassColor.getDefaultColor();
    }

    public int foliageColour(BlockAndTintGetter getter, BlockPos blockPos) {
        return getter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(getter, blockPos) : FoliageColor.getDefaultColor();
    }

}
