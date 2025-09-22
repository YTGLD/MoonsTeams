package com.ytgld.seeking_immortals.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class hidden extends MobEffect {
    public hidden() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "5454b58e-7c10-4ee4-ac27-42b58cf2c248",0.05f, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}


