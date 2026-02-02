package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.all.INightItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.ytgld.seeking_immortals.SIHandler;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public class nightmare_base_start extends nightmare implements SuperNightmare , AllTip, INightItem {
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你被赐予了异常的恢复能力");
        map.put(2,"增加百分之五十治疗");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"你被赐予了异常的恢复能力");
        map.put(2,"增加百分之五十治疗");
        return map;
    }
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).resolve().isPresent()
                    && CuriosApi.getCuriosInventory(player).resolve().get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (CuriosApi.getCuriosInventory(player).resolve().isPresent()
                    && CuriosApi.getCuriosInventory(player).resolve().get().isEquipped(Items.the_divine_fall_ring.get())){
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

    public static void damage(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_start.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_start.get())){
                    if (player.getHealth()>10) {
                        player.setHealth(player.getHealth() - 1);
                        player.getCooldowns().addCooldown(Items.nightmare_base_start.get(),10);
                    }
                }

            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (SIHandler.hascurio(slotContext.entity(), this))
            slotContext.entity().getAttributes().addTransientAttributeModifiers(gets(slotContext));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(gets(slotContext));
    }

    public Multimap<Attribute, AttributeModifier> gets(SlotContext slotContext) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        int s = Config.SERVER.nightmare_base_start.get();
        float d= s / 100f;

        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(
                UUID.fromString("d6a27da3-8d8c-4e36-8a89-807355ee9849"),"a", -d, AttributeModifier.Operation.MULTIPLY_TOTAL));
        linkedHashMultimap.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("05a2b883-672c-35b9-82ec-eccc4e0cabc2"),"asiodnm", 0.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return linkedHashMultimap;
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_start.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_start_power").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_start_pod").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_start_egg").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.wolf").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
    public UUID uuid = UUID.fromString("2cec7258-f44f-4c86-bbf9-aeabbd3a95ed");
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


