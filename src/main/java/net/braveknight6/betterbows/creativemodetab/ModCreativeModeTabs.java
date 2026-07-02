package net.braveknight6.betterbows.creativemodetab;

import net.braveknight6.betterbows.BetterBows;
import net.braveknight6.betterbows.block.ModBlocks;
import net.braveknight6.betterbows.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab BETTER_BOWS_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(BetterBows.MOD_ID, "better_bows_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.QUIVER))
                    .title(Component.translatable("creativemodetab.betterbows.better_bows_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.QUIVER);
                        output.accept(ModBlocks.BOWSMITHING_TABLE);
                    }).build());

    public static void registerModCreativeModeTabs(){
        BetterBows.LOGGER.info("Registering creative mode tabs for " + BetterBows.MOD_ID);
    }
}
