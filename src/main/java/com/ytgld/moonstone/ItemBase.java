package com.ytgld.moonstone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ItemBase extends Item implements ICurioItem {
    public ItemBase(Properties properties) {
        super(properties);
    }
    public int color (){
        return 5592575;
    }
    public int colorEQ (){
        return 16755200;
    }

    public Identifier identifier() {
        return Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId);
    }

    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {

    }

    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {

    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        return HashMultimap.create();
    }

    public final Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, Identifier s, ItemStack stack) {
        return HashMultimap.create();
    }

    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        curioTickUse(slotContext, stack);
        if (!slotContext.entity().level().isClientSide()) {
            if (!getAttributeModifiers(stack, slotContext.entity()).isEmpty()) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack, slotContext.entity()));
            }
        }
    }

    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

    }
    @Override
    public final void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        onUnequipUse(slotContext, newStack, stack);
        if (!slotContext.entity().level().isClientSide()) {
            if (!getAttributeModifiers(stack, slotContext.entity()).isEmpty()) {
                slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack, slotContext.entity()));
            }
        }
    }
    @Override
    public final void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        text(itemStack, builder, tooltipFlag);
        List<Component> components = new ArrayList<>();
        appendHoverText(itemStack, null, components, tooltipFlag);
        for (Component component : components) {
            builder.accept(component);
        }
    }
}
