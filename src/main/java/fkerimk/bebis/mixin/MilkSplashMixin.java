package fkerimk.bebis.mixin;

import fkerimk.bebis.fluids.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.jspecify.annotations.Nullable;

@Mixin(Entity.class)
public abstract class MilkSplashMixin {

    @Shadow public abstract net.minecraft.world.level.Level level();
    @Shadow public abstract @Nullable AABB getFluidInteractionBox();

    @Inject(method = "doWaterSplashEffect", at = @At("HEAD"), cancellable = true)
    private void bebis$noMilkSplash(CallbackInfo ci) {

        AABB box = this.getFluidInteractionBox();

        if (box == null) return;

        boolean touchingMilk = BlockPos.betweenClosedStream(box).anyMatch(pos -> {

            FluidState fluidState = this.level().getFluidState(pos);
            var type = fluidState.getType();
            return type == ModFluids.Milk.Source || type == ModFluids.Milk.Flowing;
        });

        if (touchingMilk) ci.cancel();
    }
}