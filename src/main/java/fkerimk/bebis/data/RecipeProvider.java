package fkerimk.bebis.data;

import fkerimk.bebis.Main;
import fkerimk.bebis.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) { super(output, registriesFuture); }

    @Override protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {

        return new net.minecraft.data.recipes.RecipeProvider(registries, output) {
            @Override public void buildRecipes() {

                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, Items.COOKIE, RecipeCategory.FOOD, ModBlocks.CookieBlock);
            }
        };
    }

    @NonNull @Override public String getName() { return Main.Id + " Recipes"; }
}
