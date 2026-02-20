package com.moonstone.moonstonemod.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nightmare_head extends nightmare  implements Nightmare {
    public static final String die = "NigDie";
    public int size = 0;
    public static void LivingDeathEvent(LivingDeathEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_head.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmareeye.get())){
                                int ss = player.deathTime;
                                if (ss<1) {

                                    if (stack.getTag() != null) {
                                        if (stack.getTag().getInt(die) <100) {
                                            stack.getTag().putInt(die, stack.getTag().getInt(die) + 1);
                                        }
                                    }
                                }
                            }
                            if (stack.is(Items.nightmare_head.get())){
                                int ss = player.deathTime;
                                if (ss<1) {

                                    if (stack.getTag() != null) {
                                        if (stack.getTag().getInt(die) <100) {
                                            stack.getTag().putInt(die, stack.getTag().getInt(die) + 1);
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
    public static void headHurt(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_head.get())) {
                if (player.hasEffect(Effects.life.get())
                        ||player.hasEffect(Effects.life_apple.get())){
                    return;
                }
                if (event.getSource() != null ){
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        if (Config.SERVER.openDamageForAttacker.get()) {
                            living.hurt(living.damageSources().dryOut(), event.getAmount() * 0.5f);
                        }
                    }
                }

                if (event.getSource() != null && !event.getSource().is(DamageTypes.MOB_ATTACK)
                        || !event.getSource().is(DamageTypes.MOB_PROJECTILE)) {
                    event.setAmount(0);
                }
                if (event.getSource() != null && event.getSource().is(DamageTypes.MOB_ATTACK)
                        || event.getSource().is(DamageTypes.MOB_PROJECTILE)
                        || event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)) {
                    player.setHealth(player.getHealth()-1);
                }
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "aa", -0.75f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }

    public static void LivingHealEvent(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_head.get())){
                event.setAmount(0);
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().hasEffect(Effects.life.get())
                ||slotContext.entity().hasEffect(Effects.life_apple.get())){
            if (slotContext.entity() instanceof Player player) {
                player.displayClientMessage(Component.translatable("moonstone.life.error").withStyle(ChatFormatting.RED), true);
            }
        }
        addHealth(slotContext, stack);
    }
    public void addHealth(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().hasEffect(Effects.life.get())
                ||slotContext.entity().hasEffect(Effects.life_apple.get())){
            return;
        }
        if (!slotContext.entity().level().isClientSide
                &&slotContext.entity().tickCount % 100 == 0){
            if (Handler.hascurio(slotContext.entity(),Items.nightmareeye.get())) {
                stack.getOrCreateTag().putString("TestTag", "TestTag");
                slotContext.entity().setHealth(slotContext.entity().getHealth() + 1);
            }
        }
    }

      @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
            if (Handler.hascurio(player, com.ytgld.seeking_immortals.init.Items.apple.get())){
                return true;
            }
            if (Handler.hascurio(player, com.ytgld.seeking_immortals.init.Items.falling_immortals.get())){
                return true;
            }

        }
        return com.moonstone.moonstonemod.Config.SERVER.canUnequipMoonstoneItem.get();
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        if (stack.getTag() != null) {
            tooltip.add(Component.literal("Now: " +-stack.getTag().getInt(die)+"%").withStyle(ChatFormatting.RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.7").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("moonstone.life.error").withStyle(ChatFormatting.LIGHT_PURPLE));

    }
}
