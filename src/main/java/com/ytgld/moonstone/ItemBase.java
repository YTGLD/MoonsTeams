package com.ytgld.moonstone;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.event.DNAModifyHandler;
import com.ytgld.moonstone.item.ICanHasInItem;
import com.ytgld.moonstone.item.IDNASequence;
import com.ytgld.moonstone.item.ToolTipDNAItem;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.*;
import java.util.function.Consumer;

public class ItemBase extends Item implements ICurioItem,ICanHasInItem , IDNASequence {
    public ItemBase(Properties properties) {super(properties);}
    public int color (){
        return 5592575;
    }
    public int colorEQ (){
        return 16755200;
    }

    public ResourceLocation identifier() {
        return ResourceLocation.fromNamespaceAndPath(Moonstone.MODID, this.getDescriptionId());
    }

    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {

    }

    public void appendHoverText(ItemStack stack, Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {

    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        return HashMultimap.create();
    }
    public Multimap<Holder<Attribute>, AttributeModifier> doStrongerDNAModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap = HashMultimap.create();
        DNAModifyHandler.doStrongerDNAModifiers(this,stack,livingEntity,attributeModifierMultimap);
        return attributeModifierMultimap;
    }
    public final Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation s, ItemStack stack) {
        return HashMultimap.create();
    }
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem) {
        if (self.getItem() instanceof ICanHasInItem canHasInItem) {
            if (canHasInItem.canUSe().contains(other.getItem())) {
                Set<String> set = self.get(DataReg.theSetString.get());
                if (set == null){
                    self.set(DataReg.theSetString.get(),new HashSet<>());
                }
                if (canHasInItem.addItemToStack(self, other.getItem())) {
                    other.shrink(1);
                    return true;
                }
                return false;
            }
        }
        if (self.getItem() instanceof IDNASequence idnaSequence) {
            if (idnaSequence.canUseHowSequence().contains(other.getItem())) {
                Set<String> set = self.get(DataReg.dna_sequence.get());
                if (set == null){
                    self.set(DataReg.dna_sequence.get(),new HashSet<>());
                }
                if (idnaSequence.addDNASequence(self, other.getItem())) {
                    other.shrink(1);
                    return true;
                }
                return false;
            }
        }
        return super.overrideOtherStackedOnMe(self, other, slot, clickAction, player, carriedItem);
    }

    @Override
    public  Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        if (maxSize() > 0 || maxDNAValue() > 0){
            return Optional.of(new ToolTipDNAItem(this, this,itemStack));
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
            if (!doStrongerDNAModifiers(stack, slotContext.entity()).isEmpty()) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(doStrongerDNAModifiers(stack, slotContext.entity()));
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
            if (!doStrongerDNAModifiers(stack, slotContext.entity()).isEmpty()) {
                slotContext.entity().getAttributes().removeAttributeModifiers(doStrongerDNAModifiers(stack, slotContext.entity()));
            }
        }
    }
    @Override
    public final void appendHoverText(ItemStack itemStack, TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        Consumer<Component> builder  = list::add;

        text(itemStack, builder, tooltipFlag);
        List<Component> components = new ArrayList<>();
        appendHoverText(itemStack, (Level)null, components, tooltipFlag);
        for (Component component : components) {
            builder.accept(component);
        }
        if (maxSize() > 0) {
            if (tooltipFlag.hasControlDown()) {
                builder.accept(Component.translatable("key.keyboard.left.control").withStyle(ChatFormatting.GRAY));
                builder.accept(Component.translatable("moonstone.use.dna.attribute").withStyle(ChatFormatting.GOLD));
                for (Item item : canUSe()) {
                    builder.accept(Component.literal("+").append(Component.translatable(item.getDescriptionId())).withStyle(ChatFormatting.BLUE));
                }
                for (Item item : canUseHowSequence()) {
                    builder.accept(Component.literal("+").append(Component.translatable(item.getDescriptionId())).withStyle(ChatFormatting.BLUE));
                }
            } else {
                builder.accept(Component.translatable("key.keyboard.left.control").withStyle(ChatFormatting.GOLD));
            }
        }
    }

    @Override
    public int maxSize() {
        return 0;
    }

    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>();
    }

    @Override
    public int maxDNAValue() {
        return 0;
    }

    @Override
    public HashSet<Item> canUseHowSequence() {
        return new HashSet<>();
    }
}
