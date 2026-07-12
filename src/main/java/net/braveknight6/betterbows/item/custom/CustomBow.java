package net.braveknight6.betterbows.item.custom;

import net.braveknight6.betterbows.tags.ModTags;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class CustomBow extends BowItem {
  public static final Predicate<ItemStack> ALL_ARROWS = ARROW_ONLY.or(itemStack -> itemStack.is(ModTags.Items.CUSTOM_ARROWS));

  public CustomBow(Properties properties) {
    super(properties);
  }

  @Override
  public Predicate<ItemStack> getAllSupportedProjectiles() {
    return ALL_ARROWS;
  }
}
