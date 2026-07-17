package net.braveknight6.betterbows.datagen;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import net.braveknight6.betterbows.BetterBows;
import net.braveknight6.betterbows.block.ModBlocks;
import net.braveknight6.betterbows.item.ModItems;
import net.braveknight6.betterbows.recipe.CustomCraftingRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(
            FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(
            HolderLookup.Provider registries, RecipeOutput output) {
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

                customBuildRecipes(output, "redstone_bow", Items.REDSTONE, ModItems.REDSTONE_BOW);
            }

            private void customBuildRecipes(
                    RecipeOutput recipeOutput, String name, Item craftItem, Item outputItem) {
                ShapedRecipePattern pattern = ShapedRecipePattern.of(
                        Map.of(
                                'S', Ingredient.of(Items.STICK),
                                'R', Ingredient.of(craftItem),
                                '~', Ingredient.of(Items.STRING)),
                        " R~",
                        "RS~",
                        " R~");

                ShapedRecipe shapedLayout = new ShapedRecipe(
                        new Recipe.CommonInfo(false),
                        new CraftingRecipe.CraftingBookInfo(CraftingBookCategory.EQUIPMENT, ""),
                        pattern,
                        new ItemStackTemplate(outputItem));

                recipeOutput.accept(
                        ResourceKey.create(Registries.RECIPE, BetterBows.id(name)),
                        new CustomCraftingRecipe(shapedLayout),
                        null);
            }
        };
    }

    @Override
    public String getName() {
        return "BetterBows Recipes";
    }
}
