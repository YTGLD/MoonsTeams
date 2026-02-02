package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.all.INightItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.SIHandler;
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
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public class nightmare_base_stone extends nightmare implements SuperNightmare, AllTip, INightItem {

    public static void LivingHurtEvent(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.nightmare_base_stone.get())) {
                if (SIHandler.hascurio(player,Items.candle.get())){
                    return;
                }
                if (SIHandler.hascurio(player, Items.blood_god.get())) {
                    return;
                }
                if (player.getHealth() >= player.getMaxHealth()) {

                    double d = Config.SERVER.nightmare_base_stone.get();
                    event.setAmount((float) (event.getAmount() * d+1));

                    if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_stone.get())) {
                        if (event.getAmount() > player.getHealth()) {
                            event.setAmount(0);
                            player.setHealth(1);
                            player.getCooldowns().addCooldown(Items.nightmare_base_stone.get(),200);
                        }
                    }
                }
            }
        }
    }
    public static final String  uDead = "undead";
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getTag()==null){
            stack.getOrCreateTag();
        }
        if (slotContext.entity() instanceof Player player){
            if (!player.level().isClientSide) {
                float lv = player.getHealth() / player.getMaxHealth();
                lv *= 100;
                int now = (int) (100 -(lv));
                if (stack.getTag()==null){
                    stack.getOrCreateTag();
                }
                if (stack.getTag()!=null){
                    stack.getTag().putInt(uDead,now);
                }
            }

        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        super.onEquip(slotContext,prevStack,stack);
        if (slotContext.entity() instanceof Player player){
            if (!player.level().isClientSide) {
                player.getAttributes().addTransientAttributeModifiers(ad(stack));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(ad(stack));
    }

    public Multimap<Attribute, AttributeModifier> ad(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.getTag()!=null) {
            int lvl = stack.getTag().getInt(uDead);
            float heal = 0.85f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;



            modifiers.put(AttReg.heal.get(),new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"asd",
                    heal, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    speed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    damage, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ATTACK_SPEED,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    attSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
            modifiers.put(Attributes.ARMOR,new AttributeModifier(UUID.fromString("8b8434b7-4246-3db8-89e4-a51a52bc8fc9"),"iosnadjas",
                    armor, AttributeModifier.Operation.MULTIPLY_BASE));



        }
        return modifiers;
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



    public UUID uuid = UUID.fromString("21e69a07-3790-44c6-8606-b710651e265c");
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
    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.1").withStyle(ChatFormatting.RED));

        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_stone_virus").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_stone_meet").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_stone_brain").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.end_bone").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));


    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"这是试炼  磨练你的意志");
        map.put(2,"生命值越低 各方面属性越高");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"这是试炼  磨练你的意志");
        map.put(2,"生命值越低 各方面属性越高");
        return map;
    }
}

