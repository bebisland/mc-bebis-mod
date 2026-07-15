package fkerimk.bebis.mixin;

import fkerimk.bebis.fluids.ModFluids;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.material.FluidState;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class) public abstract class MilkFogMixin {

    @Inject(method = "computeFogColor", at = @At("RETURN"))
    private void bebis$whiteMilkFog(Camera camera, float partialTicks, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f dest, CallbackInfo ci) {

        BlockPos pos = camera.blockPosition();
        FluidState fluidState = level.getFluidState(pos);

        var fluidType = fluidState.getType();

        if (fluidType == ModFluids.Milk.Source || fluidType == ModFluids.Milk.Flowing)
            dest.set(1.0F, 1.0F, 1.0F, dest.w());
    }
}