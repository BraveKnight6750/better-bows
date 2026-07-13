package net.braveknight6.betterbows.datagen;

import java.util.concurrent.CompletableFuture;
import net.braveknight6.betterbows.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
  public ModBlockLootTableProvider(
      FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    super(packOutput, registriesFuture);
  }

  @Override
  public void generate() {
    dropSelf(ModBlocks.BOWSMITHING_TABLE);
  }
}
