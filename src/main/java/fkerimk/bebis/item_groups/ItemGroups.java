package fkerimk.bebis.item_groups;

import fkerimk.bebis.Main;
import fkerimk.bebis.blocks.ModBlocks;
import fkerimk.bebis.items.ModItems;
import fkerimk.bebis.fluids.ModFluids;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

public class ItemGroups {

    public static CreativeModeTab Bebis;

    public static void Register() {

        Register("bebis", ModItems.Bebis, (parameters, output) -> {

            output.accept(ModBlocks.CookieBlock);
            output.accept(ModFluids.Milk.Bucket);
            output.accept(ModFluids.ChocolateMilk.Bucket);
            output.accept(ModFluids.StrawberryMilk.Bucket);
            output.accept(ModItems.Bebis);
            output.accept(ModItems.BebisSpawnEgg);
        });

        Register(CreativeModeTabs.NATURAL_BLOCKS, output ->  {

            output.accept(ModBlocks.CookieBlock);
        });

        Register(CreativeModeTabs.FOOD_AND_DRINKS, output ->  {

            output.accept(ModFluids.ChocolateMilk.Bucket);
            output.accept(ModFluids.StrawberryMilk.Bucket);
        });

        Register(CreativeModeTabs.INGREDIENTS, output ->  {

            output.accept(ModItems.Bebis);
        });

        Register(CreativeModeTabs.SPAWN_EGGS, output ->  {

            output.accept(ModItems.BebisSpawnEgg);
        });
    }

    public static void Register (String name, Item icon, CreativeModeTab.DisplayItemsGenerator items) {

        Bebis = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Main.Id(name),
            FabricCreativeModeTab.builder()
                .icon(() -> new ItemStack(icon))
                .title(Component.translatable("itemGroup.%s.%s".formatted(Main.Id, name)))
                .displayItems(items)
                .build());
    }

    public static void Register (ResourceKey<CreativeModeTab> tab, CreativeModeTabEvents.ModifyOutput items) {

        CreativeModeTabEvents.modifyOutputEvent(tab).register(items);
    }
}
