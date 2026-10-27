package com.ytgld.moonstone.item.ms.blood.magic;

import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheBloodBook extends BloodItem {

    public TheBloodBook(Properties properties) {
        super(properties);
    }

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
