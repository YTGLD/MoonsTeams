package com.ytgld.moonstone.effect;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Hidden extends MobEffect {
    public Hidden() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, Identifier.fromNamespaceAndPath(Moonstone.MODID,"hidden"),0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}


