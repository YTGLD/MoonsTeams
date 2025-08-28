package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class max_eye extends Item implements ICurioItem, Blood {
    public static final String blood="MaxBlood";
    public max_eye() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void Die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_eye.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.getTag() != null) {
                                if (stack.getTag().getFloat(blood)<0.5){
                                    stack.getTag().putFloat(blood,stack.getTag().getFloat(blood)+0.1f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void A(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_eye.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.getTag() != null) {
                                if (stack.getTag().getFloat(blood)>0){
                                    stack.getTag().putFloat(blood,stack.getTag().getFloat(blood)-0.1f);
                                    event.setAmount(event.getAmount()*0.1f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.getTag()==null){
            stack.getOrCreateTag();
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.getTag() != null){
            if (slotContext.entity() instanceof Player player){
                if (stack.getTag().getFloat(blood)>0) {
                    stack.getTag().putFloat(blood, stack.getTag().getFloat(blood) - 0.001f);
                    player.heal(player.getMaxHealth() / 200);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.4").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }

}
