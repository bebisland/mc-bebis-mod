package fkerimk.bebis;

import fkerimk.bebis.data.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jspecify.annotations.NonNull;

public class Data implements DataGeneratorEntrypoint {

	@Override public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {

		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelProvider::new);
		pack.addProvider(BlockTagsProvider::new);
		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(FluidTagsProvider::new);
	}
}
