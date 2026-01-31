package com.laineypowell.biomevariety.mixin;

import com.laineypowell.biomevariety.worldgen.BiomeVarietyBiomes;
import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FogRenderer.class)
public final class FogRendererMixin {

    @Inject(method = "setupFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void biomeVariety$setupFog(Camera camera, FogRenderer.FogMode fogMode, float f, boolean bl, float g, CallbackInfo ci, FogType fogType, Entity entity, FogRenderer.FogData fogData) {
        if (fogMode == FogRenderer.FogMode.FOG_TERRAIN) {
            var biome = Minecraft.getInstance().level.getBiome(camera.getBlockPosition());

            if (biome.unwrapKey().filter(BiomeVarietyBiomes.BIOMES::contains).isPresent()) {
                fogData.start = f * (float) Math.PI * 0.1f;
                fogData.end = f * 2.125f;
                fogData.shape = FogShape.CYLINDER;
            }
        }
    }
}
