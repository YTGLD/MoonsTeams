package com.moonstone.moonstonemod.item.nightmare.super_nightmare.stone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

public class nightmare_base_stone_virus extends nightmare implements SuperNightmare {
    UUID uuid = UUID.fromString("05d3c308-6531-310d-988c-a6164eaf800d");
   
    public static void aVoid(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_base_stone_virus.get())){
                player.setHealth(player.getHealth() - player.getMaxHealth()*(Config.SERVER.Nightecora.get()/100f));
            }
        }
    }
    public Multimap<Attribute, AttributeModifier> gets() {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid,"a", 1, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(AttReg.heal.get(), new AttributeModifier(uuid,"a", 1, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(AttReg.cit.get(), new AttributeModifier(uuid,"a", 1, AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(),this) ){
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets());
    }

    @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone_virus.tool.string.3").withStyle(ChatFormatting.DARK_RED));
    }

}
