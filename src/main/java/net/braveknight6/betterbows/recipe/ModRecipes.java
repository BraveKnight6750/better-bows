package net.braveknight6.betterbows.recipe;

import com.mojang.serialization.MapCodec;
import net.braveknight6.betterbows.BetterBows;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class ModRecipes {
  public static RecipeType<CustomCraftingRecipe> CUSTOM_RECIPE_TYPE =
      Registry.register(
          BuiltInRegistries.RECIPE_TYPE,
          BetterBows.id("custom_crafting"),
          new RecipeType<>() {
            @Override
            public String toString() {
              return "custom_crafting";
            }
          });

  public static final MapCodec<CustomCraftingRecipe> CODEC =
      ShapedRecipe.MAP_CODEC.xmap(
          CustomCraftingRecipe::new, CustomCraftingRecipe::getImplementation);

  public static final StreamCodec<RegistryFriendlyByteBuf, CustomCraftingRecipe> STREAM_CODEC =
      ShapedRecipe.STREAM_CODEC.map(
          CustomCraftingRecipe::new, CustomCraftingRecipe::getImplementation);

  public static RecipeSerializer<CustomCraftingRecipe> CUSTOM_SERIALIZER =
      Registry.register(
          BuiltInRegistries.RECIPE_SERIALIZER,
          BetterBows.id("custom_crafting"),
          new RecipeSerializer<>(CODEC, STREAM_CODEC));

  public static void registerRecipes() {
    BetterBows.LOGGER.info("Registering recipes for " + BetterBows.MOD_ID);
  }
}
