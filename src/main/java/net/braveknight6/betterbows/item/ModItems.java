package net.braveknight6.betterbows.item;

import java.util.function.Function;
import net.braveknight6.betterbows.BetterBows;
import net.braveknight6.betterbows.item.custom.CustomBow;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
  public static final Item QUIVER = registerItem("quiver", Item::new);

  public static final Item REDSTONE_BOW = registerItem(
      "redstone_bow", properties -> new CustomBow(properties.durability(50), 1.5F, 1.5F));

  public static Item registerItem(String name, Function<Item.Properties, Item> function) {
    return Registry.register(
        BuiltInRegistries.ITEM,
        BetterBows.id(name),
        function.apply(
            new Item.Properties().setId(ResourceKey.create(Registries.ITEM, BetterBows.id(name)))));
  }

  public static void registerModItems() {
    BetterBows.LOGGER.info("Registering mod items for " + BetterBows.MOD_ID);

    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
        .register(
            output -> {
              output.accept(QUIVER);
            });
  }

  public static ResourceKey<Item> getRK(Item item) {
    return BuiltInRegistries.ITEM.getResourceKey(item).get();
  }
}
