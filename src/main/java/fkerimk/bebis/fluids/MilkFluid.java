package fkerimk.bebis.fluids;

import fkerimk.bebis.base.ModFluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import org.jspecify.annotations.NonNull;

public abstract class MilkFluid extends ModFluid {

    public static class Source extends MilkFluid {

        public Source() { }

        @Override public int getAmount(final @NonNull FluidState fluidState) { return 8; }
        @Override public boolean isSource(final @NonNull FluidState fluidState) { return true; }
    }

    public static class Flowing extends MilkFluid {

        public Flowing() { }

        @Override protected void createFluidStateDefinition(final StateDefinition.@NonNull Builder<Fluid, FluidState> builder) {

            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override public int getAmount(final FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }
        @Override public boolean isSource(final @NonNull FluidState fluidState) { return false; }
    }
}
