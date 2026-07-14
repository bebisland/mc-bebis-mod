package fkerimk.bebis.mixin;

import fkerimk.bebis.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MilkSplashMixin {

    @Shadow public abstract Level level();
    @Shadow public abstract BlockPos blockPosition();

    @Inject(method = "doWaterSplashEffect", at = @At("HEAD"), cancellable = true)
    private void bebis$noMilkSplash(CallbackInfo ci) {

        FluidState fluidState = this.level().getFluidState(this.blockPosition());

        var fluidType = fluidState.getType();

        if (fluidType == ModFluids.Milk.Source || fluidType == ModFluids.Milk.Flowing)
            ci.cancel();
    }
}