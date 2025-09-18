package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class end_bone  extends nightmare implements SuperNightmare {
    public static void hurts(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.end_bone.get())) {
                if (player.getHealth() >= player.getMaxHealth()){
                    if ( event.getSource().getEntity() instanceof LivingEntity living) {
                        living.hurt(living.damageSources().dryOut(), event.getAmount() * 0.7f);
                    }
                    event.setAmount(event.getAmount()*0.2f);
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide){
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(slotContext.entity()));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide){
            slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(slotContext.entity()));
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.end_bone.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.end_bone.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(LivingEntity living) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        float s = 0;
        if (living.getHealth() >= living.getMaxHealth()){
            s -= 0.5f;
        }else {
            s = 0;
        }
        attributeModifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("a5e6c2ae-850b-4570-86b9-b6621fd2c30b"),"as",
                s, AttributeModifier.Operation.MULTIPLY_TOTAL));

        return attributeModifiers;

    }

}
