package net.braveknight6.betterbows.datagen;

import net.braveknight6.betterbows.block.ModBlocks;
import net.braveknight6.betterbows.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

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
    }
}
