package fkerimk.bebis;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.function.Function;

public class Blocks {

    public static Block CookieBlock;

    public static void Register () {

        CookieBlock = RegisterBlock("cookie_block", properties -> new Block(properties
            .strength(1)
            .sound(SoundType.TUFF)
        ));
    }
    public static Block RegisterBlock (String name, Function<BlockBehaviour.Properties, Block> function) {

        var id = Main.Id(name);

        Block block = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, id)));

        Registry.register(BuiltInRegistries.ITEM,id,new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, id))));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }
}
