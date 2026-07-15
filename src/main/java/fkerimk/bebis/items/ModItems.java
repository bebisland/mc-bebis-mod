package fkerimk.bebis.items;

import fkerimk.bebis.Main;
import fkerimk.bebis.entities.ModEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;

import java.util.function.Function;

public class ModItems {

    public static Item Bebis;
    public static Item BebisSpawnEgg;

    public static void Register() {

        Bebis = Register("bebis", Item::new);

        BebisSpawnEgg = Register("bebis_spawn_egg", properties -> new SpawnEggItem(properties.component(DataComponents.ENTITY_DATA, TypedEntityData.of(ModEntities.Bebis, new CompoundTag()))));
    }

    private static Item Register(String name, Function<Item.Properties, Item> function) {

        var id = Main.Id(name);

        return Registry.register(BuiltInRegistries.ITEM, id, function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }
}
