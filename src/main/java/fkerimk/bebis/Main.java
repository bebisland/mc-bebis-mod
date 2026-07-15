package fkerimk.bebis;

import fkerimk.bebis.blocks.ModBlocks;
import fkerimk.bebis.entities.ModEntities;
import fkerimk.bebis.fluids.ModFluids;
import fkerimk.bebis.item_groups.ItemGroups;
import fkerimk.bebis.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

public class Main implements ModInitializer {

	public static final String Id = "bebis";
	public static final Logger Logger = LoggerFactory.getLogger(Id);
	public static Unsafe Unsafe;

	@Override
	public void onInitialize() {

        try {

			// Unsafe
			var field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			Unsafe = (Unsafe)field.get(null);

			// Registry
            ModEntities.Register();
			ModItems.Register();
            ModBlocks.Register();
            ModFluids.Register();
            ItemGroups.Register();

        } catch (Exception exception) {

            Logger.error("\uD83D\uDEA8\uD83C\uDF6A\uD83D\uDEA8{}", exception.getMessage(), exception);
		}
    }

	public static Identifier Id(String path) {

		return Identifier.fromNamespaceAndPath(Id, path);
	}
}
