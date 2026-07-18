package com.ytgld.moonstone.item.si.nightmare.other;

import com.google.common.collect.Multimap;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

/**
 * 增加25%最大生命值
 * <P>
 * 增加40%生命恢复
 * <P>
 * <P>
 * 饮用药水的速度增加25%
 * <P>
 * <P>
 * 你最高受到5点伤害
 * <P>
 * 超过5点的伤害将转换成等数值的流血
 * <P>
 * <P>
 * 饮用治疗药水使流血伤害削弱至原先的50%
 * <P>
 * 饮用药水使流血伤害削弱至原先的95%
 */
public class BloodGod extends NightmareSmall {
    public static final int TIME = 10 * 20;

    public static final String bloodTime = "bloodTime";
    public static final String bloodDamage = "bloodDamage";


    public static final String giveName_kill = "giveName_kill";
    public static final String giveName_heal = "giveName_heal";
    public static final String giveName_damage = "giveName_damage";
    public static final String give_End = "give_End";

    public BloodGod(Properties properties) {
        super(properties);
    }

    public static void hurtOfBlood(LivingEntityUseItemEvent.Start event){
        if (event.getItem().getUseAnimation() == ItemUseAnimation.DRINK) {
            if (event.getEntity() instanceof Player player) {
                if (SIHandler.hascurio(player, Items.blood_god.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.blood_god.get())) {
                                    CompoundTag compoundTag = stack.get(DataReg.tag);
                                    if (compoundTag != null) {
                                        event.setDuration((int) (event.getDuration() * 0.7f));
                                        if (!player.getCooldowns().isOnCooldown(Items.blood_god.get().getDefaultInstance())) {
                                            if (compoundTag.getFloatOr(bloodDamage,0) > 0) {
                                                if (event.getItem().getItem() instanceof PotionItem potionItem) {
                                                    compoundTag.putFloat(bloodDamage, compoundTag.getFloatOr(bloodDamage,0) * 0.75F);

                                                    player.getCooldowns().addCooldown(Items.blood_god.get().getDefaultInstance(), 600);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }
    public static void hurtOfBlood(LivingDamageEvent.Pre event){
        if (!event.getSource().is(DamageTypes.GENERIC_KILL)) {
            if (event.getEntity() instanceof Player player){
                if (SIHandler.hascurio(player,Items.blood_god.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.blood_god.get())) {
                                    CompoundTag compoundTag = stack.get(DataReg.tag);
                                    if (compoundTag != null) {
                                        float s = event.getNewDamage() - 5;
                                        if (s <= 5) {
                                        } else {
                                            compoundTag.putFloat(bloodDamage, compoundTag.getFloatOr(bloodDamage,0) + s);
                                            compoundTag.putInt(bloodTime, TIME);
                                            event.setNewDamage(5);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            CompoundTag compoundTag = stack.get(DataReg.tag);
            if (compoundTag != null) {
                if (compoundTag.getIntOr(bloodTime,0)<= 0){
                    compoundTag.putFloat(bloodDamage, 0);
                }
                if (compoundTag.getIntOr(bloodDamage,0)<= 0){
                    compoundTag.putFloat(bloodTime, 0);
                }
                if (player.isDeadOrDying()) {
                    compoundTag.putFloat(bloodDamage, 0);
                    compoundTag.putFloat(bloodTime, 0);
                }

                if (!player.level().isClientSide()
                        && player.tickCount% 20 == 1){
                    if (compoundTag.getIntOr(bloodTime,0)>0&&compoundTag.getFloatOr(bloodDamage,0)>0) {
                        compoundTag.putInt(bloodTime, compoundTag.getIntOr(bloodTime,0) - TIME / 10);
                        float dmg  = compoundTag.getFloatOr(bloodDamage,0) / 10f;
                        compoundTag.putFloat(bloodDamage, compoundTag.getFloatOr(bloodDamage,0) -dmg);
                        player.hurt(player.damageSources().genericKill(),dmg);

                    }
                }
            }
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity living) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(identifier(), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(AttReg.heal, new AttributeModifier(identifier(), 0.4, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);


        pTooltipComponents.add(Component.translatable("item.blood_god.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.blood_god.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.blood_god.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.blood_god.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.blood_god.tool.string.5").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.blood_god.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.blood_god.tool.string.7").withStyle(ChatFormatting.DARK_RED));
    }

}
