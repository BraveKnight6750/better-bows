package net.braveknight6.betterbows.client;

import net.braveknight6.betterbows.client.screen.BowsmithingTableScreen;
import net.braveknight6.betterbows.menutype.ModMenuType;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class BetterBowsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuType.BOWSMITHING_TABLE_MENU_TYPE, BowsmithingTableScreen::new);
    }
}
