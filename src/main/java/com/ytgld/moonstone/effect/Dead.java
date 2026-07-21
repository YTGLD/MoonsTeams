package com.ytgld.moonstone.effect;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Dead extends MobEffect {
    public Dead() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, Identifier.fromNamespaceAndPath(Moonstone.MODID,"dead"),-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED,Identifier.fromNamespaceAndPath(Moonstone.MODID,"dead"),-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,Identifier.fromNamespaceAndPath(Moonstone.MODID,"dead"),-0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.ARMOR,Identifier.fromNamespaceAndPath(Moonstone.MODID,"dead"),-0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(AttReg.heal,Identifier.fromNamespaceAndPath(Moonstone.MODID,"dead"),-0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    }
}


