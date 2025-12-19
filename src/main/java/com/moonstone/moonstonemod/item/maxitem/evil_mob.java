package com.moonstone.moonstonemod.item.maxitem;

import com.all.IGreedyItem;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class evil_mob extends CommonItem implements Die , IGreedyItem {
    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.evil_mob.tool.string.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.evil_mob.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFF483D8B))));
        p_41423_.add(Component.translatable("item.evil_mob.tool.string.4").withStyle(ChatFormatting.RED));
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        var s = super.getAttributeModifiers(slotContext, uuid, stack);
        CuriosApi
                .addSlotModifier(s, "ncrdna",uuid, 3, AttributeModifier.Operation.ADDITION);

        CuriosApi
                .addSlotModifier(s, "dna", uuid, 4, AttributeModifier.Operation.ADDITION);
        return s;
    }

}
