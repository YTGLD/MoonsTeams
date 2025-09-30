package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone;

import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class nightmare_base_stone_meet extends nightmare implements SuperNightmare {
    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.nightmare_base_stone_meet.tool.string.1").withStyle(ChatFormatting.DARK_RED));

        p_41423_.add(Component.translatable("item.moonstone.ectoplasmstar").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.literal("   ").append( Component.translatable("item.moonstone.ectoplasmstar.nightmare_base_stone_meet")).withStyle(ChatFormatting.DARK_RED));

        p_41423_.add(Component.translatable("item.moonstone.mayhemcrystal").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.literal("   ").append( Component.translatable("item.moonstone.ectoplasmstar.mayhemcrystal")).withStyle(ChatFormatting.DARK_RED));

        p_41423_.add(Component.translatable("item.moonstone.maxamout").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.literal("   ").append( Component.translatable("item.moonstone.ectoplasmstar.maxamout")).withStyle(ChatFormatting.DARK_RED));
    }
}
