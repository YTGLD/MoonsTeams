package com.moonstone.moonstonemod.item.TheNecora.god;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.GodDNA;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class GodPutrefactive  extends GodDNA {


    public static void eat(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.god_putrefactive.get())){
                if (event.getItem().getUseAnimation() == UseAnim.EAT){
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,600,1));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,1));

                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,480,1));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,480,1));

                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,300,1));
                }
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier>modifierMultimap = HashMultimap.create();


        modifierMultimap.put(AttReg.heal.get(), new AttributeModifier(uuid,"asd", 0.25f, AttributeModifier.Operation.MULTIPLY_BASE));

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

