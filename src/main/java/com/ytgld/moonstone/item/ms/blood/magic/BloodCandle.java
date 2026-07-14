package com.ytgld.moonstone.item.ms.blood.magic;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.OwnerBlood;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloodCandle extends BloodItem {
    public BloodCandle(Properties properties) {
        super(properties);
    }

    public static final String hasOwnerBlood = "hasOwnerBlood";

    public static void event(Player player) {
        if (Handler.hascurio(player, Items.blood_candle.asItem())) {
            if (player.level().isClientSide()) {
                return;
            }
            CompoundTag compoundTag = player.getPersistentData();
            if (!player.getCooldowns().isOnCooldown(Items.blood_candle.asItem().getDefaultInstance())) {
                if (!compoundTag.getBooleanOr(hasOwnerBlood, false)) {
                    OwnerBlood EndComing = new OwnerBlood(EntityTs.owner_blood_.get(), player.level());
                    EndComing.setPos(player.position());
                    EndComing.setOwner(player);
                    EndComing.tame(player);
                    player.level().addFreshEntity(EndComing);
                    compoundTag.putBoolean(hasOwnerBlood, true);
                    player.getCooldowns().addCooldown(Items.blood_candle.asItem().getDefaultInstance(), 10);
                } else {
                    compoundTag.putBoolean(hasOwnerBlood, false);
                    player.getCooldowns().addCooldown(Items.blood_candle.asItem().getDefaultInstance(), 10);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (p_41424_.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));

            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.2").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }


}

