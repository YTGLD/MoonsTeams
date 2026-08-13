package com.ytgld.moonstone.effect;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Blade extends MobEffect {
    public Blade() {
        super(MobEffectCategory.HARMFUL, 0xffff0000);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, "blade"), 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}

