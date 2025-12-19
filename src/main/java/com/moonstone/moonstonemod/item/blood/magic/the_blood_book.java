package com.moonstone.moonstonemod.item.blood.magic;

import com.all.IRedItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import com.moonstone.moonstonemod.moonstoneitem.extend.TheNecoraIC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class the_blood_book  extends TheNecoraIC implements IRedItem {

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.the_blood_book.tool.string").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.the_blood_book.tool.string.1").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.the_blood_book.tool.string.3").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.the_blood_book.tool.string.4").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.the_blood_book.tool.string.5").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.the_blood_book.tool.string.6").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.literal(""));
    }
}
