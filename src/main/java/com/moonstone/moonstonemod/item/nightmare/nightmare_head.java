package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
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

public class nightmare_head extends nightmare {
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
                if (event.getSource() != null ){
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        living.hurt(living.damageSources().magic(),event.getAmount() * 5);
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
    public static void LivingHealEvent(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_head.get())){
                event.setAmount(0);
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide
                &&slotContext.entity().tickCount % 100 == 0){
            stack.getOrCreateTag().putString("TestTag","TestTag");
            slotContext.entity().setHealth(slotContext.entity().getHealth()+1);
        }
    }


    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.8").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        if (stack.getTag() != null) {
            tooltip.add(Component.literal("Now: " +-stack.getTag().getInt(die)+"%").withStyle(ChatFormatting.RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.7").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));

    }
}
