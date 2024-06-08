package com.moonstone.moonstonemod.item.plague.ALL;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.plague;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
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
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class parasite extends plague implements ICurioItem {
    public String lvl_parasite = "lvl";
    public String sizeLevel = "sizeLevel";
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        stack.getOrCreateTag().putString("ytgld","ytgld");
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext .entity() instanceof Player player){
            if (player.getFoodData().getFoodLevel() < 7){
                if (stack.getOrCreateTag().getInt(sizeLevel)>400) {
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 1);
                    stack.getOrCreateTag().putInt(sizeLevel, stack.getOrCreateTag().getInt(sizeLevel)- 20);
                }
            }

            if (player.getFoodData().getFoodLevel() < player.getFoodData().getFoodLevel() /2) {
                if (!player.level().isClientSide&&player.tickCount% 30 == 0) {
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));
                }
            }
            player.getAttributes().addTransientAttributeModifiers(AttributeModifiers(player,stack));
            if (!player.level().isClientSide&&player.tickCount% 30 == 0){
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 120, 0));
                if (stack.getOrCreateTag().getInt(sizeLevel) >= 300 && stack.getOrCreateTag().getInt(sizeLevel) < 600){
                    stack.getOrCreateTag().putInt(lvl_parasite, 1);
                }
                if (stack.getOrCreateTag().getInt(sizeLevel) >= 600 && stack.getOrCreateTag().getInt(sizeLevel) < 900){
                    stack.getOrCreateTag().putInt(lvl_parasite, 2);
                }
                if (stack.getOrCreateTag().getInt(sizeLevel) >= 900){
                    stack.getOrCreateTag().putInt(lvl_parasite, 3);
                }
            }
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext .entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(AttributeModifiers(player,stack));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.parasite.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.parasite.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.parasite.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.parasite.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.parasite.tool.string.4").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.parasite.tool.string.5").withStyle(ChatFormatting.RED));


            if (stack.getOrCreateTag().getInt(lvl_parasite)==0){//在0级时的寄生虫
                tooltip.add(Component.translatable("无属性").withStyle(ChatFormatting.DARK_RED));
            }else if (stack.getOrCreateTag().getInt(lvl_parasite)>=1 &&stack.getOrCreateTag().getInt(lvl_parasite)<2){//在1级时的寄生虫
                tooltip.add(Component.translatable(" +1 伤害").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(" +2 生命上限").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(" +4 护甲").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable(" -1 食物的饥饿值").withStyle(ChatFormatting.RED));
            }else if (stack.getOrCreateTag().getInt(lvl_parasite)>=2&&stack.getOrCreateTag().getInt(lvl_parasite)<3){//在2级时的寄生虫
                tooltip.add(Component.translatable(" +2 伤害").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(" +4 生命上限").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(" +6 护甲").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable(" -1 食物的饱和度").withStyle(ChatFormatting.RED));
            }else if (stack.getOrCreateTag().getInt(lvl_parasite)>=3){//在3级时的寄生虫
                tooltip.add(Component.translatable(" 在饥饿值大于60%时，攻击附加40%伤害").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable(" -1 食物的饥饿值").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(" -1 食物的饱和度").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(""));
                tooltip.add(Component.translatable(" +2 伤害").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(" +4 生命上限").withStyle(ChatFormatting.RED));
                tooltip.add(Component.translatable(" +6 护甲").withStyle(ChatFormatting.RED));

            }
        } else {
            tooltip.add(Component.translatable("按下SHIFT查看").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("目前等级："+stack.getOrCreateTag().getInt(lvl_parasite)).withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("目前点数："+stack.getOrCreateTag().getInt(sizeLevel)).withStyle(ChatFormatting.DARK_RED));
    }
    public Multimap<Attribute, AttributeModifier> AttributeModifiers(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        int si = 0;
        if (stack.getOrCreateTag().getInt(lvl_parasite) >= 1){
            si = 1;
        }
        if (stack.getOrCreateTag().getInt(lvl_parasite) >= 2){
            si *= 2;
        }

        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("6216d3be-96f5-3b55-9404-8ae4f0bc7784"), MoonStoneMod.MODID+":parasite", si, AttributeModifier.Operation.ADDITION));
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("6216d3be-96f5-3b55-9404-8ae4f0bc7784"), MoonStoneMod.MODID+":parasite", si * 2, AttributeModifier.Operation.ADDITION));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("6216d3be-96f5-3b55-9404-8ae4f0bc7784"), MoonStoneMod.MODID+":parasite", si*3, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
}
