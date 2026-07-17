package net.braveknight6.betterbows.block;

import java.util.function.Function;
import net.braveknight6.betterbows.BetterBows;
import net.braveknight6.betterbows.block.custom.BowsmithingTableBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
  public static final Block BOWSMITHING_TABLE =
      registerBlock(
          "bowsmithing_table",
          properties ->
              new BowsmithingTableBlock(
                  properties.strength(2.5F).sound(SoundType.WOOD).ignitedByLava()));

  private static Block registerBlock(
      String name, Function<BlockBehaviour.Properties, Block> function) {
    Block toRegister =
        function.apply(
            BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, BetterBows.id(name))));
    registerBlockItem(name, toRegister);
    return Registry.register(BuiltInRegistries.BLOCK, BetterBows.id(name), toRegister);
  }

  private static void registerBlockItem(String name, Block block) {
    Registry.register(
        BuiltInRegistries.ITEM,
        BetterBows.id(name),
        new BlockItem(
            block,
            new Item.Properties()
                .useBlockDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, BetterBows.id(name)))));
  }

  public static ResourceKey<Block> getRK(Block block) {
    return BuiltInRegistries.BLOCK.getResourceKey(block).get();
  }

  public static void registerModBlocks() {
    BetterBows.LOGGER.info("Registering blocks for " + BetterBows.MOD_ID);
  }
}
