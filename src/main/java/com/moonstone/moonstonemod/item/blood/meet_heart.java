package com.moonstone.moonstonemod.item.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class meet_heart extends TheNecoraIC {
    public static void addEffectMeet(Player entity){
        if (!entity.level().isClientSide) {
            int maxTime = 20 * 60 * 30;
            if (Handler.hascurio(entity, Items.meet_heart.get())) {
                entity.addEffect(new MobEffectInstance(Effects.meet.get(), 1200, 0, false, false));
                MobEffectInstance effect = entity.getEffect(Effects.meet.get());
                if (effect != null) {
                    if (effect.getAmplifier() < 9 && effect.getDuration() < maxTime) {
                        entity.addEffect(new MobEffectInstance(Effects.meet.get(),
                                effect.getDuration() + 1200,
                                effect.getAmplifier() + 1)
                        );
                    } else {
                        entity.addEffect(new MobEffectInstance(Effects.meet.get(), maxTime, 9, false, false));
                    }
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().hasEffect(Effects.meet.get())) {
            if (slotContext.entity().tickCount % 30 ==1) {
                slotContext.entity().addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 2,false,false));
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "a", -10, AttributeModifier.Operation.ADDITION));

        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.meet_heart.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.meet_heart.tool.string.1").withStyle(ChatFormatting.GOLD));
    }
}
