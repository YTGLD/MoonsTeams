package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class nightmare_base_black_eye_red  extends nightmare {
    public static final String aty  = "NightmareRed";

      public static void kill(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player ){
            if (Handler.hascurio(player, Items.nightmare_base_black_eye_red.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is( Items.nightmare_base_black_eye_red.get())) {
                                if (stack.getTag() != null) {
                                    if (stack.getTag().getInt(aty) < 50) {
                                        stack.getTag().putInt(aty, stack.getTag().getInt(aty) + 5);
                                    }
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
        super.onEquip(slotContext, prevStack, stack);
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.getTag() == null){
           stack.getOrCreateTag();
        } else {
            if (slotContext.entity().tickCount % 20 ==1) {
                if (stack.getTag().getInt(aty)>0) {
                    stack.getTag().putInt(aty, stack.getTag().getInt(aty) - 1);
                }
            }
        }
    }
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers( ItemStack stack) {
        Multimap<Attribute, AttributeModifier> get = HashMultimap.create();
        if (stack.getTag()!=null) {
            double as = stack.getTag().getInt(aty) / 100f;

            for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {

                get.put(attribute.get(), new AttributeModifier(UUID.fromString("11e9bf70-b06d-37b5-8fe5-cb8c8adfd22c"),"a", as, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
        return get;
    }

    
 @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
    }
}
