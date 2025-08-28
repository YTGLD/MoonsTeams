package com.moonstone.moonstonemod.item.maxitem.rage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import java.util.UUID;

public class rage_charm extends Item  implements ICurioItem,Blood {
    public static final String atr = "RageAtt";


    public rage_charm() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    public static void die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_charm.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.rage_charm.get())){
                                if (stack.getTag() != null){
                                    if (stack.getTag().getFloat(atr)<100) {
                                        stack.getTag().putFloat(atr, stack.getTag().getFloat(atr) + 4);
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
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().tickCount % 10 == 0) {
            if (stack.getTag() != null) {
                if (stack.getTag().getFloat(atr) > 0) {
                    stack.getTag().putFloat(atr, stack.getTag().getFloat(atr) - 0.25f);
                }
            } else {
                stack.getOrCreateTag();
            }
        }
        if (!slotContext.entity().level().isClientSide) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(g(stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(g(stack));

    }

    public Multimap<Attribute, AttributeModifier>  g(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifierMultimap =HashMultimap.create();
        if (stack.getTag() != null) {
            float r = stack.getTag().getFloat(atr);//r=1~100
            r/=100;//==1%~100^

            attributeModifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("8b435ab7-751f-377f-8d6e-243e4ae9abf2"), "a",
                                r/2, AttributeModifier.Operation.MULTIPLY_BASE));
            attributeModifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("8b435ab7-751f-377f-8d6e-243e4ae9abf2"), "a",
                                r/4, AttributeModifier.Operation.MULTIPLY_BASE));

            attributeModifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("8b435ab7-751f-377f-8d6e-243e4ae9abf2"), "a",
                                r/3.5F, AttributeModifier.Operation.MULTIPLY_BASE));

           attributeModifierMultimap.put(AttReg.heal.get(), new AttributeModifier(UUID.fromString("8b435ab7-751f-377f-8d6e-243e4ae9abf2"), "a",
                               r/2.5f, AttributeModifier.Operation.MULTIPLY_BASE));

            attributeModifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.fromString("8b435ab7-751f-377f-8d6e-243e4ae9abf2"), "a",
                                r, AttributeModifier.Operation.MULTIPLY_BASE));

        }

        return attributeModifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);


        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.rage_charm.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.rage_charm.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
            if (pStack.getTag() != null) {
                float r = pStack.getTag().getFloat(atr);//r=1~100
                pTooltipComponents.add(Component.translatable("attribute.name.generic.attack_damage").append("：").append(String.valueOf(r/2)).append("%").withStyle(ChatFormatting.DARK_RED));
                pTooltipComponents.add(Component.translatable("attribute.name.generic.attack_speed").append("：").append(String.valueOf(r/4)).append("%").withStyle(ChatFormatting.DARK_RED));
                pTooltipComponents.add(Component.translatable("attribute.name.generic.armor").append("：").append(String.valueOf(r/3.5)).append("%").withStyle(ChatFormatting.DARK_RED));
                pTooltipComponents.add(Component.translatable("attribute.name.moonstone.heal").append("：").append(String.valueOf(r/2.5)).append("%").withStyle(ChatFormatting.DARK_RED));
                pTooltipComponents.add(Component.translatable("attribute.name.generic.knockback_resistance").append("：").append(String.valueOf(r)).append("%").withStyle(ChatFormatting.DARK_RED));
            }
        }
    }
}

