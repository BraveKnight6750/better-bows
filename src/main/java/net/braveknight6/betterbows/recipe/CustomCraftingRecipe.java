package net.braveknight6.betterbows.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class CustomCraftingRecipe implements Recipe<CraftingInput> {
    private final ShapedRecipe implementation;

    public CustomCraftingRecipe(ShapedRecipe implementation){
        this.implementation = implementation;
    }

    public ShapedRecipe getImplementation(){
        return this.implementation;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return this.implementation.matches(input, level);
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return this.implementation.assemble(input);
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {
        return ModRecipes.CUSTOM_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<CraftingInput>> getType() {
        return ModRecipes.CUSTOM_RECIPE_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
