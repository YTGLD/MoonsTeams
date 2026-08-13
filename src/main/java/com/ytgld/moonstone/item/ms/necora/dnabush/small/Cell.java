package com.ytgld.moonstone.item.ms.necora.dnabush.small;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.event.key.Keys;
import com.ytgld.moonstone.item.IKet;
import com.ytgld.moonstone.item.ms.necora.TheNecoraDNABush;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

public class Cell extends TheNecoraDNABush implements IKet {
    public Cell(Properties properties) {
        super(properties);
    }

    @Override
    public int maxSize() {
        return 2;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.cell.tool.string.2").withStyle(Style.EMPTY.withColor(0xffff0000)));
        tooltip.add(Component.translatable("item.cell.tool.string.3").withStyle(Style.EMPTY.withColor(0xffff0000)));

        tooltip.add(Component.literal("").withStyle(ChatFormatting.DARK_RED));
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable("item.cell.tool.string").withStyle(ChatFormatting.DARK_RED));
        }else {
            tooltip.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "dnabush", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                        2, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }
    @Override
    public KeyMapping theKeyMapping() {
        return Keys.ZombieC;
    }
}
