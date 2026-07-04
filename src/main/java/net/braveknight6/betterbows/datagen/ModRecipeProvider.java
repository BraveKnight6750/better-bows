package net.braveknight6.betterbows.datagen;

import net.braveknight6.betterbows.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
               shaped(RecipeCategory.MISC, ModBlocks.BOWSMITHING_TABLE)
                       .pattern("SS")
                       .pattern("WW")
                       .pattern("WW")
                       .define('S', Items.STRING)
                       .define('W', ItemTags.PLANKS)
                       .unlockedBy(getHasName(Items.OAK_PLANKS), has(Items.OAK_PLANKS))
                       .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "BetterBows Recipes";
    }
}
