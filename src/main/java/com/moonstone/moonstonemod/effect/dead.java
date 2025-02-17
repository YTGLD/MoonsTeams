package com.moonstone.moonstonemod.effect;

import com.moonstone.moonstonemod.init.AttReg;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class dead extends MobEffect {
    public dead() {
        super(MobEffectCategory.NEUTRAL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,"0de21b13-73b3-37ba-84f2-92c78b6712c2",-0.05f, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED,"0de21b13-73b3-37ba-84f2-92c78b6712c2",-0.05f, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,"0de21b13-73b3-37ba-84f2-92c78b6712c2",-0.05f, AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(Attributes.ARMOR,"0de21b13-73b3-37ba-84f2-92c78b6712c2",-0.2f, AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(AttReg.heal.get(),"0de21b13-73b3-37ba-84f2-92c78b6712c2",-0.2f, AttributeModifier.Operation.MULTIPLY_BASE);


    }
}
