package fkerimk.bebis.data;

import fkerimk.bebis.Blocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import org.jspecify.annotations.NonNull;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricPackOutput output) { super(output); }

    @Override public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {

        blockModelGenerators.createTrivialCube(Blocks.CookieBlock);
    }

    @Override public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {

    }
}
