package net.braveknight6.betterbows.tags;

import net.braveknight6.betterbows.BetterBows;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{

        private static TagKey<Block> createTag(String name){
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, name));
        }
    }

    public static class Items{
        public static final TagKey<Item> CUSTOM_BOWS = createTag("custom_bows");
        public static final TagKey<Item> CUSTOM_ARROWS = createTag("custom_arrows");

        private static TagKey<Item> createTag(String name){
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, name));
        }
    }
}
