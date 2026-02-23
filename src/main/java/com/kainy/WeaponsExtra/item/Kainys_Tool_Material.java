package com.kainy.WeaponsExtra.item;

import net.minecraft.block.Block;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import java.util.function.Supplier;

public enum Kainys_Tool_Material implements ToolMaterial {
    ingot1(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            Integer.MAX_VALUE,                   // 正无穷耐久
            Float.POSITIVE_INFINITY,             // 正无穷挖掘速度
            Float.POSITIVE_INFINITY,             // 正无穷攻击伤害
            Integer.MAX_VALUE,                   // 正无穷附魔次数（不昂贵）
            () -> Ingredient.ofItems(Kainys_Items.ingot1));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    Kainys_Tool_Material(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public ToolComponent createComponent(TagKey<Block> tag) {
        return ToolMaterial.super.createComponent(tag);
    }
}