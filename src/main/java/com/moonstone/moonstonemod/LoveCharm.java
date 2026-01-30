package com.moonstone.moonstonemod;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.renderer.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoveCharm extends Item implements ICurioItem {
    public LoveCharm() {
        super(new Properties().rarity(Rarity.create("love", ChatFormatting.GOLD)));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        MutableComponent co = component.copy();
        int b = (int) (255 * Math.sin(NewEvent.time / 50));
        if (b < 0) {
            b = 0;
        }

        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255,255,255,b))));
        return co;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "curio", uuid , 1, AttributeModifier.Operation.ADDITION);
        linkedHashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID+this.getDescriptionId(),
                1, AttributeModifier.Operation.ADDITION));
        linkedHashMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID+this.getDescriptionId(),
                4, AttributeModifier.Operation.ADDITION));
        linkedHashMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID+this.getDescriptionId(),
                2, AttributeModifier.Operation.ADDITION));

        linkedHashMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID+this.getDescriptionId(),
                0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        linkedHashMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID+this.getDescriptionId(),
                0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        return linkedHashMultimap;
    }

    public static final String name = "compoundTagMyLoveCharm";

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        CompoundTag compoundTag =p_41421_.getTag();
        if (compoundTag!=null) {
            p_41423_.add(Component.literal(compoundTag.getString(name)).append(Component.literal("的礼物！").withStyle(ChatFormatting.GOLD)));
        }
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        List<Component> components = new ArrayList<>();
        for (Component component : tooltips) {
            MutableComponent mutableComponent = component.copy();
            mutableComponent.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255, 250, 150, 50))));
            components.add((mutableComponent));
        }
        return components;
    }
}
