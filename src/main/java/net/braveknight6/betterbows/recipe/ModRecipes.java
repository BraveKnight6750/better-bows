package net.braveknight6.betterbows.recipe;

import com.mojang.serialization.MapCodec;
import net.braveknight6.betterbows.BetterBows;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class ModRecipes {
    public static RecipeType<CustomCraftingRecipe> CUSTOM_RECIPE_TYPE;
    public static RecipeSerializer<CustomCraftingRecipe> CUSTOM_SERIALIZER;

    public static void registerRecipes() {
        CUSTOM_RECIPE_TYPE = Registry.register(
                BuiltInRegistries.RECIPE_TYPE,
                Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, "custom_crafting"),
                new RecipeType<>() {
                    @Override
                    public String toString() {
                        return "custom_crafting";
                    }
                }
        );

        // 2. Map data serialization structures by wrapping vanilla's ShapedRecipe Codecs
        MapCodec<CustomCraftingRecipe> codec = ShapedRecipe.MAP_CODEC
                .xmap(CustomCraftingRecipe::new, CustomCraftingRecipe::getImplementation);

        StreamCodec<RegistryFriendlyByteBuf, CustomCraftingRecipe> streamCodec = ShapedRecipe.STREAM_CODEC
                .map(CustomCraftingRecipe::new, CustomCraftingRecipe::getImplementation);

        // 3. Register the Serializer to read JSON structures from your datapack
        CUSTOM_SERIALIZER = Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, "custom_crafting"),
                new RecipeSerializer<>(codec, streamCodec)
        );
    }
}
