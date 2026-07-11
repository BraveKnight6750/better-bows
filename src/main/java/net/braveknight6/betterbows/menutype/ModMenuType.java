package net.braveknight6.betterbows.menutype;

import net.braveknight6.betterbows.BetterBows;
import net.braveknight6.betterbows.menutype.custom.BowsmithingTableMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;

public class ModMenuType {
  public static final MenuType<BowsmithingTableMenu> BOWSMITHING_TABLE_MENU_TYPE =
      registerMenuType(
          "bowsmithing_table_menu_type",
          (containerId, inventory) ->
              new BowsmithingTableMenu(containerId, inventory, ContainerLevelAccess.NULL));

  public static <T extends AbstractContainerMenu> MenuType<T> registerMenuType(
      String name, MenuType.MenuSupplier<T> factory) {
    return Registry.register(
        BuiltInRegistries.MENU,
        BetterBows.id(name),
        new MenuType<>(factory, FeatureFlags.DEFAULT_FLAGS));
  }

  public static void registerModMenuTypes() {
    BetterBows.LOGGER.info("Registering menu types for " + BetterBows.MOD_ID);
  }
}
