package net.braveknight6.betterbows.menu;

import java.util.Optional;
import net.braveknight6.betterbows.block.ModBlocks;
import net.braveknight6.betterbows.menutype.ModMenuType;
import net.braveknight6.betterbows.recipe.CustomCraftingRecipe;
import net.braveknight6.betterbows.recipe.ModRecipes;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;

public class BowsmithingTableMenu extends AbstractContainerMenu {
  private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 3, 3);
  private final ResultContainer resultSlots = new ResultContainer();
  private final ContainerLevelAccess access;
  private final Player player;

  public BowsmithingTableMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
    super(ModMenuType.BOWSMITHING_TABLE_MENU_TYPE, containerId);
    this.access = access;
    this.player = inventory.player;

    this.addSlot(new ResultSlot(this.player, this.craftSlots, this.resultSlots, 0, 124, 35));

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 3; ++col) {
        this.addSlot(new Slot(this.craftSlots, col + row * 3, 30 + col * 18, 17 + row * 18));
      }
    }

    for (int row = 0; row < 3; ++row) {
      for (int col = 0; col < 9; ++col) {
        this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
      }
    }

    for (int col = 0; col < 9; ++col) {
      this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
    }
  }

  @Override
  public ItemStack quickMoveStack(Player player, int slotIndex) {
    ItemStack itemStack = ItemStack.EMPTY;
    Slot slot = this.slots.get(slotIndex);

    if (slot != null && slot.hasItem()) {
      ItemStack itemStack2 = slot.getItem();
      itemStack = itemStack2.copy();

      if (slotIndex == 0) { // Taking item out of the Result slot
        this.access.execute((level, pos) -> itemStack2.getItem().onCraftedBy(itemStack2, player));
        // Move result stack into player inventory slots (Indices 10 to 46)
        if (!this.moveItemStackTo(itemStack2, 10, 46, true)) {
          return ItemStack.EMPTY;
        }
        slot.onQuickCraft(itemStack2, itemStack);
      } else if (slotIndex >= 10
          && slotIndex < 46) { // Shift clicking from inventory INTO the crafting grid
        if (!this.moveItemStackTo(itemStack2, 1, 10, false)) {
          if (slotIndex < 37) {
            if (!this.moveItemStackTo(itemStack2, 37, 46, false)) {
              return ItemStack.EMPTY;
            }
          } else if (!this.moveItemStackTo(itemStack2, 10, 37, false)) {
            return ItemStack.EMPTY;
          }
        }
      } else if (!this.moveItemStackTo(
          itemStack2, 10, 46, false)) { // Grid back to player inventory
        return ItemStack.EMPTY;
      }

      if (itemStack2.isEmpty()) {
        slot.set(ItemStack.EMPTY);
      } else {
        slot.setChanged();
      }

      if (itemStack2.getCount() == itemStack.getCount()) {
        return ItemStack.EMPTY;
      }

      slot.onTake(player, itemStack2);
      if (slotIndex == 0) {
        player.drop(itemStack2, false);
      }
    }
    return itemStack;
  }

  @Override
  public boolean stillValid(Player player) {
    return stillValid(this.access, player, ModBlocks.BOWSMITHING_TABLE);
  }

  @Override
  public void slotsChanged(Container container) {
    this.access.execute(
        (level, pos) ->
            updateCraftingResult(this, level, this.player, this.craftSlots, this.resultSlots));
  }

  @Override
  public void removed(Player player) {
    super.removed(player);
    this.access.execute((level, pos) -> this.clearContainer(player, this.craftSlots));
  }

  public static void updateCraftingResult(
      AbstractContainerMenu menu,
      Level level,
      Player player,
      CraftingContainer craftingContainer,
      ResultContainer resultContainer) {
    if (!level.isClientSide()) {
      ServerPlayer serverPlayer = (ServerPlayer) player;
      ItemStack resultStack = ItemStack.EMPTY;

      CraftingInput craftingInput = craftingContainer.asCraftInput();

      Optional<RecipeHolder<CustomCraftingRecipe>> match =
          ((RecipeManager) level.recipeAccess())
              .getRecipeFor(ModRecipes.CUSTOM_RECIPE_TYPE, craftingInput, level);

      if (match.isPresent()) {
        resultStack = match.get().value().assemble(craftingInput);
      }

      resultContainer.setItem(0, resultStack);
      menu.setRemoteSlot(0, resultStack);
      serverPlayer.connection.send(
          new ClientboundContainerSetSlotPacket(
              menu.containerId, menu.incrementStateId(), 0, resultStack));
    }
  }
}
