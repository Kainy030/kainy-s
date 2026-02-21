package com.kainy.WeaponsExtra.item;

import com.kainy.WeaponsExtra.KainysWeaponsExtra;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

//以下为增加MOD专属物品栏
public class Kainys_Items_Group {
    public static final ItemGroup KainysWeaponsExtra_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(KainysWeaponsExtra.MOD_ID,"kainys-weapons-extra"),
            FabricItemGroup.builder().displayName(Text.translatable("§1K§2a§3i§4n§5y§6'§7s §8M§9o§ad§bs§c:§dW§ee§1a§2p§3o§4n§5s §6E§7x§8t§9r§aa§b"))
                    .entries((displayContext, entries) -> {
                        //以下为注册MOD物品到物品栏
                        entries.add(Kainys_Items.ingot1);
                        entries.add(Kainys_Items.ingot2);
                        entries.add(Kainys_Items.ingot3);
                        entries.add(Kainys_Items.finally_sword);
                    }).build());
    public static void registerModItemGroup(){

    }
}