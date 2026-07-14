package fkerimk.bebis;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Tabs {

    public static CreativeModeTab Bebis;

    public static void Register() {

        Register("bebis", Items.COOKIE, (parameters, output) -> {
            output.accept(ModBlocks.CookieBlock);
            output.accept(ModFluids.Milk.Bucket);
        });
    }

    public static void Register (String name, Item icon, CreativeModeTab.DisplayItemsGenerator displayItems) {

        Bebis = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Main.Id(name),
            FabricCreativeModeTab.builder()
                .icon(() -> new ItemStack(icon))
                .title(Component.translatable("inventory.%s.%s".formatted(Main.Id, name)))
                .displayItems(displayItems)
                .build());
    }
}
