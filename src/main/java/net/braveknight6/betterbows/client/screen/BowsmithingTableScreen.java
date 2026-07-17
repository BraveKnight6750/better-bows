package net.braveknight6.betterbows.client.screen;

import net.braveknight6.betterbows.BetterBows;
import net.braveknight6.betterbows.menutype.custom.BowsmithingTableMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class BowsmithingTableScreen extends AbstractContainerScreen<BowsmithingTableMenu> {
    // Replace with your actual UI texture path
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            BetterBows.MOD_ID, "textures/gui/container/bowsmithing_table.png");

    public BowsmithingTableScreen(
            BowsmithingTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    public void extractBackground(
            GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, float a) {
        int x = this.leftPos;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphicsExtractor.blit(
                RenderPipelines.GUI_TEXTURED,
                TEXTURE,
                x,
                y,
                0,
                0,
                this.imageWidth,
                this.imageHeight,
                256,
                256);
    }
}
