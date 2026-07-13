package net.braveknight6.betterbows.datagen;

import net.braveknight6.betterbows.block.ModBlocks;
import net.braveknight6.betterbows.item.ModItems;
import net.braveknight6.betterbows.item.custom.CustomBow;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.world.item.Item;

public class ModModelProvider extends FabricModelProvider {

  public ModModelProvider(FabricPackOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
    blockModelGenerators.createTrivialBlock(ModBlocks.BOWSMITHING_TABLE, TexturedModel.ORIENTABLE);
  }

  @Override
  public void generateItemModels(ItemModelGenerators itemModelGenerators) {
    itemModelGenerators.generateFlatItem(ModItems.QUIVER, ModelTemplates.FLAT_ITEM);
    generateCustomBow(itemModelGenerators, ModItems.REDSTONE_BOW);
  }

  private void generateCustomBow(ItemModelGenerators generator, Item item) {
    if (item instanceof CustomBow bow){
      generator.createFlatItemModel(item, ModelTemplates.BOW);
      ItemModel.Unbaked bowModel = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(bow));
      ItemModel.Unbaked pulling0 = ItemModelUtils.plainModel(generator.createFlatItemModel(bow, "_pulling_0", ModelTemplates.BOW));
      ItemModel.Unbaked pulling1 = ItemModelUtils.plainModel(generator.createFlatItemModel(bow, "_pulling_1", ModelTemplates.BOW));
      ItemModel.Unbaked pulling2 = ItemModelUtils.plainModel(generator.createFlatItemModel(bow, "_pulling_2", ModelTemplates.BOW));

      float dynamicScale = 0.05F * bow.speedMod;

      generator.itemModelOutput.accept(
              bow,
              ItemModelUtils.conditional(
                      ItemModelUtils.isUsingItem(),
                      ItemModelUtils.rangeSelect(
                              new UseDuration(false),
                              dynamicScale,
                              pulling0,
                              ItemModelUtils.override(pulling1, 0.65F),
                              ItemModelUtils.override(pulling2, 0.9F)
                      ),
                      bowModel
              )
      );
    }
  }
}
