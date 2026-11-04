package com.ytgld.moonstone.item.ms;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.event.NewEvent;
import com.ytgld.moonstone.other.DataReg;
import com.ytgld.moonstone.other.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LoveCharm extends CommonItem{
    public LoveCharm(Properties properties) {
        super(properties);
    }

    public static void renderBack(RenderTooltipEvent.Color event) {
        if (event.getItemStack().getItem() instanceof LoveCharm item) {
            event.setBackground(Light.ARGB.color(235, 20, 20, 0));
            event.setBorderEnd(Light.ARGB.color(255, 200, 200, 0));
            event.setBorderStart(Light.ARGB.color(255, 200, 200, 0));
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(identifier(),
                1, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(identifier(),
                4, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(identifier(),
                2, AttributeModifier.Operation.ADD_VALUE));
        linkedHashMultimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(identifier(),
                0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(identifier(),
                0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }
    @Override
    public Component getName(ItemStack itemStack) {
        Component component = super.getName(itemStack);
        MutableComponent co = component.copy();
        int b = (int) (255 * Math.sin(NewEvent.time / 50));
        if (b < 0) {
            b = 0;
        }
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255,255,255,b))));
        return co;
    }

    public static final String name = "compoundTagMyLoveCharm";

    @Override
    public void text(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.text(itemStack, builder, tooltipFlag);
        CompoundTag compoundTag =itemStack.get(DataReg.tag);
        if (compoundTag!=null){
            builder.accept(Component.literal(compoundTag.getString(name))
                    .append(Component.literal("的礼物！").withStyle(ChatFormatting.GOLD)));

        }
    }
    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, TooltipContext context, ItemStack stack) {
        List<Component> components = new ArrayList<>();
        for (Component component : tooltips) {
            MutableComponent mutableComponent = component.copy();
            mutableComponent.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255, 250, 150, 50))));
            components.add((mutableComponent));
        }
        return components;
    }
}
