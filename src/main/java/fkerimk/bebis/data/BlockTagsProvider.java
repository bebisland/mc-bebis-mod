package fkerimk.bebis.data;

import fkerimk.bebis.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class BlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {


    public BlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) { super(output, registryLookupFuture); }

    @Override protected void addTags(HolderLookup.@NonNull Provider registries) {

        builder(BlockTags.MINEABLE_WITH_HOE)
            .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CookieBlock).orElseThrow());

        builder(BlockTags.NEEDS_STONE_TOOL)
            .add(BuiltInRegistries.BLOCK.getResourceKey(ModBlocks.CookieBlock).orElseThrow());
    }
}
