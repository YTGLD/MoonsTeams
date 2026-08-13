package com.ytgld.moonstone.item.ms.necora.dna.god;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.GodDNA;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.List;

public class GodPutrefactive extends GodDNA {


    public GodPutrefactive(Properties properties) {
        super(properties);
    }

    public static void eat(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.GodPutrefactive.get())) {
                if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 1));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1));

                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 480, 1));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 480, 1));

                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1));
                }
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {

        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();


        modifierMultimap.put(AttReg.heal, new AttributeModifier(identifier(), 0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }


    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.god_putrefactive.tool.string").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.god_putrefactive.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.literal(""));
    }
}

