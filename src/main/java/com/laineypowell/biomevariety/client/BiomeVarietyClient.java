package com.laineypowell.biomevariety.client;

import com.laineypowell.biomevariety.BiomeVarietyBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public final class BiomeVarietyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BiomeVarietyBlocks.BUTTONWEED, RenderType.cutout());
    }
}
