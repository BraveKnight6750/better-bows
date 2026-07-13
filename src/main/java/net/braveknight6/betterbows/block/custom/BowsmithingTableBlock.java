package net.braveknight6.betterbows.block.custom;

import com.mojang.serialization.MapCodec;
import net.braveknight6.betterbows.menutype.custom.BowsmithingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class BowsmithingTableBlock extends Block {
  public static final MapCodec<BowsmithingTableBlock> CODEC =
      simpleCodec(BowsmithingTableBlock::new);
  public static final Component CONTAINER_TITLE =
      Component.translatable("block.betterbows.bowsmithing_table");

  @Override
  protected MapCodec<? extends Block> codec() {
    return CODEC;
  }

  public BowsmithingTableBlock(Properties properties) {
    super(properties);
  }

  @Override
  protected InteractionResult useWithoutItem(
      BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (!level.isClientSide()) {
      player.openMenu(state.getMenuProvider(level, pos));
    }

    return InteractionResult.SUCCESS;
  }

  @Override
  protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
    return new SimpleMenuProvider(
        (containerId, inventory, player) ->
            new BowsmithingTableMenu(
                containerId, inventory, ContainerLevelAccess.create(level, pos)),
        CONTAINER_TITLE);
  }
}
