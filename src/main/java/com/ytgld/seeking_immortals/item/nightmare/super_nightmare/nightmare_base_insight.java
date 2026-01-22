package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.all.INightItem;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

import static com.ytgld.seeking_immortals.event.old.AdvancementEvt.giveItem;

public class nightmare_base_insight extends nightmare implements SuperNightmare, AllTip, INightItem {
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }
    public static void exp(LivingExperienceDropEvent event){
        if (SIHandler.hascurio(event.getAttackingPlayer(),Items.nightmare_base_insight.get())){
            event.setDroppedExperience(event.getDroppedExperience()*2);
        }
    }
    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你感受到了另类的气息");
        map.put(2,"增加百分之百经验掉落");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你感受到了另类的气息");
        map.put(2,"增加百分之百经验掉落");
        return map;
    }
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).resolve().isPresent()
                    && CuriosApi.getCuriosInventory(player).resolve().get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
            if (Config.SERVER.canUnequipMoonstoneItem.get()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag()!=null) {
            if (stack.getTag().getBoolean("give_nightmare_base_insight_drug")){
                return;
            }
            if (!stack.getTag().getBoolean("give_nightmare_base_insight_drug")) {
                if (slotContext.entity() instanceof Player player) {
                    List<Integer> integers = new ArrayList<>();
                    int a = 0;
                    Collection<MobEffectInstance> collection = player.getActiveEffects();
                    if (!collection.isEmpty()) {
                        for (MobEffectInstance effectInstance : collection) {
                            if (effectInstance.getEffect().isBeneficial()) {
                                integers.add(1);
                            }
                        }
                    }
                    for (int ignored : integers) {
                        a++;
                    }
                    if (a >= Config.SERVER.give_nightmare_base_insight_drug.get()) {
                        giveItem(player,new ItemStack(Items.nightmare_base_insight_drug.get()));
                        stack.getTag().putBoolean("give_nightmare_base_insight_drug",true);
                    }
                }
            }
        }else {
            stack.getOrCreateTag();
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight.tool.string.1").withStyle(ChatFormatting.DARK_RED));

        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_insight_collapse").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_insight_insane").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_insight_drug").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.ring").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.hidden_blade").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
    public UUID uuid = UUID.fromString("e8211177-e66d-4dde-a057-d6e3291ce768");
    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        AttributeModifier attributeModifier =  new AttributeModifier(uuid, this.getDescriptionId(), 1, AttributeModifier.Operation.ADDITION);
        if (p_41406_ instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("nightmare").ifPresent(stacks -> {
                for (UUID uuid : stacks.getModifiers().keySet()){
                    if (uuid == this.uuid){
                        return;
                    }
                }
                stacks.addPermanentModifier(attributeModifier);
            }));
        }
    }

}
