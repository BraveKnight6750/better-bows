package net.braveknight6.betterbows.item.custom;

import net.braveknight6.betterbows.tags.ModTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Predicate;

public class CustomBow extends BowItem {
  public static final Predicate<ItemStack> ALL_ARROWS = ARROW_ONLY.or(itemStack -> itemStack.is(ModTags.Items.CUSTOM_ARROWS));
  public float powerMod;
  public float speedMod;

  public CustomBow(Properties properties) {
    this(properties, 1.0F, 1.0F);
  }
  public CustomBow(Properties properties, float powerMod, float speedMod) {
    super(properties);
    this.powerMod = powerMod;
    this.speedMod = speedMod;
  }

  @Override
  public boolean releaseUsing(final ItemStack itemStack, final Level level, final LivingEntity entity, final int remainingTime) {
    if (entity instanceof Player player) {
      ItemStack projectile = player.getProjectile(itemStack);
      if (projectile.isEmpty()) {
        return false;
      }

      float timeHeld = (this.getUseDuration(itemStack, entity) - remainingTime) * this.speedMod;
      float pow = this.getPowerForTimeAndItem(timeHeld);
      if (pow < 0.1) {
        return false;
      }

      List<ItemStack> firedProjectiles = draw(itemStack, projectile, player);
      if (level instanceof ServerLevel serverLevel && !firedProjectiles.isEmpty()) {
        this.shoot(serverLevel, player, player.getUsedItemHand(), itemStack, firedProjectiles, pow * 3.0F * this.powerMod, 1.0F, pow == 1.0F, null);
      }

      level.playSound(
              null,
              player.getX(),
              player.getY(),
              player.getZ(),
              SoundEvents.ARROW_SHOOT,
              SoundSource.PLAYERS,
              1.0F,
              1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pow * 0.5F
      );
      player.awardStat(Stats.ITEM_USED.get(this));
      return true;
    } else {
      return false;
    }
  }

  public float getPowerForTimeAndItem(final float timeHeld) {
    float pow = timeHeld / 20.0F;
    pow = (pow * pow + pow * 2.0F) / 3.0F;
    if (pow > 1.0F) {
      pow = 1.0F;
    }

    return pow;
  }

  @Override
  public Predicate<ItemStack> getAllSupportedProjectiles() {
    return ALL_ARROWS;
  }
}
