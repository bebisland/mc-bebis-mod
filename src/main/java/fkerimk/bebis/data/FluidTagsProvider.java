package fkerimk.bebis.data;

import fkerimk.bebis.fluids.ModFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.FluidTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class FluidTagsProvider extends FabricTagsProvider.FluidTagsProvider {
    public FluidTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) { super(output, registryLookupFuture); }

    @Override protected void addTags(HolderLookup.@NonNull Provider registries) {

        builder(FluidTags.WATER).add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.Milk.Source).orElseThrow());
        builder(FluidTags.WATER).add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.Milk.Flowing).orElseThrow());

        builder(FluidTags.WATER).add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.ChocolateMilk.Source).orElseThrow());
        builder(FluidTags.WATER).add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.ChocolateMilk.Flowing).orElseThrow());

        builder(FluidTags.WATER).add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.StrawberryMilk.Source).orElseThrow());
        builder(FluidTags.WATER).add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.StrawberryMilk.Flowing).orElseThrow());
    }
}
