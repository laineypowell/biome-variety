package com.laineypowell.biomevariety.client;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.GrassColor;

public final class BiomeVarietyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BiomeVarietyBlocks.BUTTONWEED, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BiomeVarietyBlocks.SILT_GRASS_BLOCK, RenderType.cutout());

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) ->
                blockAndTintGetter != null && blockPos != null
                    ? BiomeColors.getAverageGrassColor(blockAndTintGetter, blockPos)
                    : GrassColor.getDefaultColor(), BiomeVarietyBlocks.SILT_GRASS_BLOCK);
    }
}
