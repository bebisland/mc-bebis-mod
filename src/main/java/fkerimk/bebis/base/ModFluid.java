package fkerimk.bebis.base;

import fkerimk.bebis.fluids.ModFluids.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public abstract class ModFluid extends FlowingFluid {

    public FluidReg Reg;

    @NonNull @Override public Fluid getSource() { return Reg.Source; }
    @NonNull @Override public Fluid getFlowing() { return Reg.Flowing; }
    @NonNull @Override public Item getBucket() { return Reg.Bucket; }

    @Override public int getTickDelay(final @NonNull LevelReader level) { return Reg.TickDelay; }

    @Override protected boolean canConvertToSource(final ServerLevel level) { return level.getGameRules().get(GameRules.WATER_SOURCE_CONVERSION); }

    @Override protected void beforeDestroyingBlock(final @NonNull LevelAccessor level, final @NonNull BlockPos pos, final BlockState state) {

        BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockEntity);
    }

    @NonNull @Override public BlockState createLegacyBlock(final @NonNull FluidState fluidState) { return Reg.Block.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));  }

    @Override protected float getExplosionResistance() { return 100.0F; }

    @NonNull @Override public Optional<SoundEvent> getPickupSound() { return Optional.of(SoundEvents.BUCKET_FILL); }

    @Override public boolean isSame(final @NonNull Fluid other) { return other == Reg.Source || other == Reg.Flowing; }

    @Override protected void entityInside(final @NonNull Level level, final @NonNull BlockPos pos, final @NonNull Entity entity, final InsideBlockEffectApplier effectApplier) { effectApplier.apply(InsideBlockEffectType.EXTINGUISH); }

    @Override public int getSlopeFindDistance(final @NonNull LevelReader level) {
        return 4;
    }

    @Override public int getDropOff(final @NonNull LevelReader level) { return 1; }


    @Override public boolean canBeReplacedWith(final @NonNull FluidState state, final @NonNull BlockGetter level, final @NonNull BlockPos pos, final @NonNull Fluid other, final @NonNull Direction direction) {

        return direction == Direction.DOWN && !other.is(FluidTags.WATER);
    }

    //@Override public void animateTick(final @NonNull Level level, final @NonNull BlockPos pos, final FluidState fluidState, final @NonNull RandomSource random) {

    //    if (!fluidState.isSource() && !(Boolean)fluidState.getValue(FALLING)) {
    //        if (random.nextInt(64) == 0) {
    //            level.playLocalSound((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, SoundEvents.WATER_AMBIENT, SoundSource.AMBIENT, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
    //        }
    //    } else if (random.nextInt(10) == 0) {
    //        level.addParticle(ParticleTypes.UNDERWATER, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + random.nextDouble(), (double)pos.getZ() + random.nextDouble(), 0.0F, 0.0F, 0.0F);
    //    }
    //}

    //@Nullable @Override public ParticleOptions getDripParticle() {

    //    return ParticleTypes.DRIPPING_WATER;
    //}
}
