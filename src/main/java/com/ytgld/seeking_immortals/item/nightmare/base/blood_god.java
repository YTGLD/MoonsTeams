package com.ytgld.seeking_immortals.item.nightmare.base;

import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.SIHandler;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.renderer.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.jetbrains.annotations.NotNull;
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
public class blood_god  extends nightmare implements SuperNightmare, AllTip {
    public static final int TIME = 10 * 20;

    public static final String bloodTime = "bloodTime";
    public static final String bloodDamage = "bloodDamage";


    public static final String giveName_kill = "giveName_kill";
    public static final String giveName_heal = "giveName_heal";
    public static final String giveName_damage = "giveName_damage";
    public static final String give_End = "give_End";


    @Override
    public int color(ItemStack stack) {
        return Light.ARGB.color(255,255,50,50);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }



    public static void hurtOfBlood(LivingEntityUseItemEvent.Start event){
        if (event.getItem().getUseAnimation() == UseAnim.DRINK) {
            if (event.getEntity() instanceof Player player) {
                if (SIHandler.hascurio(player,Items.blood_god.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.blood_god.get())) {
                                    CompoundTag compoundTag = stack.getTag();
                                    if (compoundTag != null) {
                                        event.setDuration((int) (event.getDuration() * 0.7f));
                                        if (!player.getCooldowns().isOnCooldown(Items.blood_god.get())) {
                                            if (compoundTag.getFloat(bloodDamage) > 0) {
                                                if (event.getItem().getItem() instanceof PotionItem potionItem) {
                                                    Potion potion = PotionUtils.getPotion(potionItem.getDefaultInstance());
                                                    if (potion.getEffects().stream().anyMatch(effect -> effect.getEffect().equals(MobEffects.HEAL))) {
                                                        compoundTag.putFloat(bloodDamage, compoundTag.getFloat(bloodDamage) * 0.5F);
                                                    } else {
                                                        compoundTag.putFloat(bloodDamage, compoundTag.getFloat(bloodDamage) * 0.95f);
                                                    }
                                                    player.getCooldowns().addCooldown(Items.blood_god.get(), 600);
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
    public static void hurtOfBlood(LivingDamageEvent event){
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
                                    CompoundTag compoundTag = stack.getTag();
                                    if (compoundTag != null) {
                                        float s = event.getAmount() - 5;
                                        if (s <= 5) {
                                        } else {
                                            compoundTag.putFloat(bloodDamage, compoundTag.getFloat(bloodDamage) + s);
                                            compoundTag.putInt(bloodTime, TIME);
                                            event.setAmount(5);
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
            CompoundTag compoundTag = stack.getTag();
            if (compoundTag != null) {
                if (compoundTag.getInt(bloodTime)<= 0){
                    compoundTag.putFloat(bloodDamage, 0);
                }
                if (compoundTag.getInt(bloodDamage)<= 0){
                    compoundTag.putFloat(bloodTime, 0);
                }
                if (player.isDeadOrDying()) {
                    compoundTag.putFloat(bloodDamage, 0);
                    compoundTag.putFloat(bloodTime, 0);
                }

                if (!player.level().isClientSide
                        && player.tickCount% 20 == 1){
                    if (compoundTag.getInt(bloodTime)>0&&compoundTag.getFloat(bloodDamage)>0) {
                        compoundTag.putInt(bloodTime, compoundTag.getInt(bloodTime) - TIME / 10);
                        float dmg  = compoundTag.getFloat(bloodDamage) / 10f;
                        compoundTag.putFloat(bloodDamage, compoundTag.getFloat(bloodDamage) -dmg);
                        player.hurt(player.damageSources().genericKill(),dmg);

                    }
                }
            }
        }
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid,"a", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(AttReg.heal.get(), new AttributeModifier(uuid,"sss", 0.4, AttributeModifier.Operation.MULTIPLY_BASE));
        return linkedHashMultimap;
    }
    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"血祭血神，颅献颅座");
        map.put(2,"Blood for the Blood God");
        return map;
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

    @Override
    public int maxLevel(ItemStack stack) {
        return 1;
    }

    @Override
    public int nowLevel(ItemStack stack) {
        return 1;
    }
    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"血祭血神，颅献颅座");
        map.put(2,"Blood for the Blood God");
        return map;
    }
}
