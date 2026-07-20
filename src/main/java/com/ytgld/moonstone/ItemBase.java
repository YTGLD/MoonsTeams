package com.ytgld.moonstone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.item.ToolTipImageFormStack;
import com.ytgld.moonstone.item.ICanHasInItem;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class ItemBase extends Item implements ICurioItem,ICanHasInItem {
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
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem) {
        if (self.getItem() instanceof ICanHasInItem canHasInItem) {
            if (canHasInItem.canUSe().contains(other.getItem())) {
                if (canHasInItem.addItemToStack(self, other.getItem())) {
                    other.shrink(1);
                    return true;
                }
                return false;
            }
        }
        return super.overrideOtherStackedOnMe(self, other, slot, clickAction, player, carriedItem);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        if (maxSize() > 0){
            return Optional.of(new ToolTipImageFormStack(this, itemStack));
        }
        return super.getTooltipImage(itemStack);
    }

    @Override
    public final void curioTick(SlotContext slotContext, ItemStack stack) {
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

    @Override
    public int maxSize() {
        return 0;
    }

    @Override
    public Set<Item> canUSe() {
        return Set.of();
    }
}
