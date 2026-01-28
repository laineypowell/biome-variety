package com.laineypowell.biomevariety.mixin;

import com.laineypowell.biomevariety.BiomeVarietyBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BushBlock.class)
public final class BushBlockMixin {

    @Inject(method = "mayPlaceOn", at = @At("RETURN"), cancellable = true)
    public void biomevariety$mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (blockState.is(BiomeVarietyBlockTags.GRASS_GROWS_ON)) {
            cir.setReturnValue(true);
        }
    }
}
