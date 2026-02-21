package com.kainy.WeaponsExtra.datagen;

import com.kainy.WeaponsExtra.item.Kainys_Items;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;


public class Kainys_Models_Provider extends FabricModelProvider {

    public Kainys_Models_Provider(FabricDataOutput output) {super(output);}

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator){
        itemModelGenerator.register(Kainys_Items.finally_sword, Models.HANDHELD);
    }


}
