package com.kainy.WeaponsExtra;

import com.kainy.WeaponsExtra.item.Kainys_Items_Group;
import com.kainy.WeaponsExtra.item.Kainys_Items;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KainysWeaponsExtra implements ModInitializer {
	public static final String MOD_ID = "kainy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Kainy's Weapons Extra was loading success!");
		Kainys_Items_Group.registerModItemGroup();
		Kainys_Items.registerKainysItems();
	}
}