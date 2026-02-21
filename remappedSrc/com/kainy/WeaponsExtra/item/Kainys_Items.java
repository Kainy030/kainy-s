package com.kainy.WeaponsExtra.item;

import com.kainy.WeaponsExtra.KainysWeaponsExtra;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Kainys_Items {
    //注册物品ingot1#测试物品1
    public static final Item ingot1 = registerItems("ingot1",new Item(new Item.Settings()));
    //注册物品ingot2#测试物品2
    public static final Item ingot2 = registerItems("ingot2",new Item(new Item.Settings()));
    //注册物品ingot3#测试物品3
    public static final Item ingot3 = registerItems("ingot3",new Item(new Item.Settings()));
    //注册物品finally_sword#终焉之剑
    public static final Item finally_sword = registerItems(
            "finally_sword", new SwordItem(Kainys_Tool_Material.ingot1,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(Kainys_Tool_Material.ingot1,
                            0, 88888888.0F))));
    public static void addKainysItemsToKainys_Item_Group(FabricItemGroupEntries fabricItemGroupEntries){
        //将ingot1添加至Fabric物品栏
       fabricItemGroupEntries.add(ingot1);
        //将ingot2添加至Fabric物品栏
       fabricItemGroupEntries.add(ingot2);
        //将ingot3添加至Fabric物品栏
        fabricItemGroupEntries.add(ingot3);
        //将finally_sword添加至Fabric物品栏
        fabricItemGroupEntries.add(finally_sword);
    }
    private static Item registerItems(String name,Item item){
        return Registry.register(Registries.ITEM,
                Identifier.of(KainysWeaponsExtra.MOD_ID,name),item);
    }
    public static void registerKainysItems(){

    }
}
