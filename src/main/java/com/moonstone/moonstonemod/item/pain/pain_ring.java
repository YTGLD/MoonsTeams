package com.moonstone.moonstonemod.item.pain;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.ThePain;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class pain_ring extends ThePain {
    public static final String pain = "PainRing";
    public static void Heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.pain_ring.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.pain_ring.get())) {
                                if (stack.getTag()!= null){

                                    if (stack.getOrCreateTag().getInt(pain)<100) {
                                        stack.getOrCreateTag().putInt(pain, stack.getOrCreateTag().getInt(pain) + 1);
                                    }
                                    event.setAmount((event.getAmount())*0.5f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void Hurt(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.pain_ring.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.pain_ring.get())) {
                                if (stack.getTag()!= null){
                                    float dam = stack.getOrCreateTag().getInt(pain);
                                    dam/=100;
                                    event.setAmount(event.getAmount()*(1+dam));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().getHealth()>=slotContext.entity().getMaxHealth()){
            stack.getOrCreateTag().remove(pain);
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.pain_ring.tool.string").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.pain_ring.tool.string.1").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.pain_ring.tool.string.2").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable(""));
        }else {
            tooltip.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("effect.minecraft.strength").append(": "+(stack.getOrCreateTag().getInt(pain))+"%").withStyle(ChatFormatting.RED));


    }
}
