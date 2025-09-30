package com.ytgld.seeking_immortals.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class meet extends MobEffect {
    public meet() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "ff3219f1-ec3f-488d-bffe-bea0c73aa9fb",0.08f, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}

