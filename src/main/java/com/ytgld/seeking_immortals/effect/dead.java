package com.ytgld.seeking_immortals.effect;

import com.moonstone.moonstonemod.init.AttReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class dead extends MobEffect {
    public dead() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "b1bb9333-11a3-41e3-b10b-7bf504aaca2e",-0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED,"b1bb9333-11a3-41e3-b10b-7bf504aaca2e",-0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,"b1bb9333-11a3-41e3-b10b-7bf504aaca2e",-0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL);

        this.addAttributeModifier(Attributes.ARMOR,"b1bb9333-11a3-41e3-b10b-7bf504aaca2e",-0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(AttReg.heal.get(),"b1bb9333-11a3-41e3-b10b-7bf504aaca2e",-0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL);

    }
}


