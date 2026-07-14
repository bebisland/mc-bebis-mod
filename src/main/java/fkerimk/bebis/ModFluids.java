package fkerimk.bebis;

import fkerimk.bebis.base.ModFluid;
import fkerimk.bebis.fluids.MilkFluid;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class ModFluids {

    public static FluidReg Milk;

    public static void Register () {

        Milk = Register("milk", 7, MilkFluid.Source::new, MilkFluid.Flowing::new, Items.MILK_BUCKET);
    }

    public static void RegisterClient (){

        RegisterClient(Milk);
    }

    public static <T extends ModFluid> FluidReg Register( String name, int tickDelay, Supplier<T> sourceFactory, Supplier<T> flowingFactory, Item bucketItem) {

        var sourceId = Main.Id("%s_fluid_source".formatted(name));
        var flowingId = Main.Id("%s_fluid_flowing".formatted(name));
        var blockId = Main.Id("%s_fluid_block".formatted(name));
        var bucketId = Main.Id("%s_bucket".formatted(name));

        T source = sourceFactory.get();
        T flowing = flowingFactory.get();

        FluidReg reg = new FluidReg(name, tickDelay);

        reg.Source = source;
        reg.Flowing = flowing;
        source.Reg = reg;
        flowing.Reg = reg;

        var srcReg = Registry.register(BuiltInRegistries.FLUID, sourceId, source);
        var flowReg = Registry.register(BuiltInRegistries.FLUID, flowingId, flowing);

        reg.Source = srcReg;
        reg.Flowing = flowReg;
        srcReg.Reg = reg;
        flowReg.Reg = reg;

        if (bucketItem == null){

            bucketItem = new BucketItem(source, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, bucketId)).craftRemainder(Items.BUCKET).stacksTo(1));
            reg.Bucket = Registry.register(BuiltInRegistries.ITEM, bucketId, bucketItem);

        } else reg.Bucket = bucketItem;

        reg.Block = Registry.register(BuiltInRegistries.BLOCK, blockId, new LiquidBlock(source, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).setId(ResourceKey.create(Registries.BLOCK, blockId))));

        return reg;
    }

    public static void RegisterClient (FluidReg reg){

        Material sourceMaterial = new Material(Main.Id("block/%s_fluid_source".formatted(reg.Name)));
        Material flowingMaterial = new Material(Main.Id("block/%s_fluid_flowing".formatted(reg.Name)));

        FluidRenderingRegistry.register(reg.Source, reg.Flowing, new FluidModel.Unbaked(sourceMaterial, flowingMaterial, null, null));
    }

    public static final class FluidReg {

        public FluidReg(String name, int tickDelay) {

            Name = name;
            TickDelay = tickDelay;
        }

        public String Name;
        public int TickDelay;

        public FlowingFluid Source;
        public FlowingFluid Flowing;
        public LiquidBlock Block;
        public Item Bucket;
    }
}
