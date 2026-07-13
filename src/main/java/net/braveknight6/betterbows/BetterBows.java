package net.braveknight6.betterbows;

import net.braveknight6.betterbows.block.ModBlocks;
import net.braveknight6.betterbows.creativemodetab.ModCreativeModeTabs;
import net.braveknight6.betterbows.item.ModItems;
import net.braveknight6.betterbows.menutype.ModMenuType;
import net.braveknight6.betterbows.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterBows implements ModInitializer {
  public static final String MOD_ID = "betterbows";

  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    ModCreativeModeTabs.registerModCreativeModeTabs();
    ModItems.registerModItems();
    ModBlocks.registerModBlocks();
    ModRecipes.registerRecipes();
    ModMenuType.registerModMenuTypes();
  }

  public static Identifier id(String path) {
    return Identifier.fromNamespaceAndPath(MOD_ID, path);
  }
}
