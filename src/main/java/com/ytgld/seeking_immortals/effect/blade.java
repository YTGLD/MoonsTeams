package com.ytgld.seeking_immortals.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class blade extends MobEffect {
    public blade() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "748c86bf-f6a4-496e-9595-50d23b562010",0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}

