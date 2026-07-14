package com.ytgld.moonstone.item.ms.blood;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class MaxEye extends BloodItem {
    public static final String blood = "MaxBlood";

    public MaxEye(Properties properties) {
        super(properties);
    }

    public static void Die(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.max_eye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.get(DataReg.tag) != null) {
                                if (stack.get(DataReg.tag).getFloatOr(blood, 0f) < 0.5) {
                                    stack.get(DataReg.tag).putFloat(blood, stack.get(DataReg.tag).getFloatOr(blood, 0f) + 0.1f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public static void A(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.max_eye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.get(DataReg.tag) != null) {
                                if (stack.get(DataReg.tag).getFloatOr(blood, 0f) > 0) {
                                    stack.get(DataReg.tag).putFloat(blood, stack.get(DataReg.tag).getFloatOr(blood, 0f) - 0.1f);
                                    event.setNewDamage(event.getNewDamage() * 0.1f);
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
        Handler.stackCreateTag(stack);
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue() + 1);
        Handler.stackCreateTag(stack);
        if (stack.get(DataReg.tag) != null) {
            if (slotContext.entity() instanceof Player player) {
                if (stack.get(DataReg.tag).getFloatOr(blood, 0f) > 0) {
                    stack.get(DataReg.tag).putFloat(blood, stack.get(DataReg.tag).getFloatOr(blood, 0f) - 0.001f);
                    player.heal(player.getMaxHealth() / 200);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (p_41424_.hasShiftDown()) {
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
