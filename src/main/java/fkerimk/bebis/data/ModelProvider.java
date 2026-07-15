package fkerimk.bebis.data;

import fkerimk.bebis.blocks.ModBlocks;
import fkerimk.bebis.items.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricPackOutput output) { super(output); }

    @Override public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {

        blockModelGenerators.createTrivialCube(ModBlocks.CookieBlock);
    }

    @Override public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {

        itemModelGenerators.generateFlatItem(ModItems.Bebis, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BebisSpawnEgg, ModelTemplates.FLAT_ITEM);
    }
}
