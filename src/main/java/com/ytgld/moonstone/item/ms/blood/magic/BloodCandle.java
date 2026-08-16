package com.ytgld.moonstone.item.ms.blood.magic;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.OwnerBlood;
import com.ytgld.moonstone.event.key.Keys;
import com.ytgld.moonstone.item.IKet;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BloodCandle extends BloodItem implements IKet {
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
            if (!player.getCooldowns().isOnCooldown(Items.blood_candle.asItem())) {
                if (!compoundTag.getBoolean(hasOwnerBlood)) {
                    OwnerBlood EndComing = new OwnerBlood(EntityTs.owner_blood_.get(), player.level());
                    EndComing.setPos(player.position());
                    EndComing.setOwnerUUID(player.getUUID());
                    EndComing.tame(player);
                    player.level().addFreshEntity(EndComing);
                    compoundTag.putBoolean(hasOwnerBlood, true);
                    player.getCooldowns().addCooldown(Items.blood_candle.asItem(), 10);
                } else {
                    compoundTag.putBoolean(hasOwnerBlood, false);
                    player.getCooldowns().addCooldown(Items.blood_candle.asItem(), 10);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_,  Level p_41422_, List<Component> pTooltipComponents, TooltipFlag p_41424_) {
        if (p_41424_.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));

            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string.2").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
    @Override
    public KeyMapping theKeyMapping() {
        return Keys.KEY_MAPPING_LAZY_R;
    }
    @Override
    public HashSet<Item> canUSe() {
        return new HashSet<>( Set.of(
                Items.owner_blood_eye.asItem(),
                Items.owner_blood_attack_eye.asItem(),
                Items.owner_blood_speed_eye.asItem(),
                Items.owner_blood_boom_eye.asItem(),
                Items.owner_blood_effect_eye.asItem(),
                Items.owner_blood_vex.asItem(),
                Items.owner_blood_earth.asItem()
        ));
    }
    @Override
    public int maxSize() {
        return 3;
    }
}

