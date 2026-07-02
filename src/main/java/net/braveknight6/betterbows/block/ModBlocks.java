package net.braveknight6.betterbows.block;

import net.braveknight6.betterbows.BetterBows;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function){
       Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, name))));
       registerBlockItem(name, toRegister);
       return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, name)))));
    }

    public static void registerModBlocks(){
        BetterBows.LOGGER.info("Registering blocks for " + BetterBows.MOD_ID);
    }
}
