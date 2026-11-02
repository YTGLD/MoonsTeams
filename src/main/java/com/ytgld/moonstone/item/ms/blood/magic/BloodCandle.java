package com.ytgld.moonstone.item.ms.blood.magic;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.OwnerBlood;
import com.ytgld.moonstone.event.key.Keys;
import com.ytgld.moonstone.item.IKet;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

public class BloodCandle extends BloodItem implements IKet {
    public BloodCandle(Properties properties) {
        super(properties);
    }

    public static final String hasOwnerBlood = "hasOwnerBlood";

    public static void event(Player player) {
        if (Handler.hascurio(player, InitItems.blood_candle.asItem())) {
            if (player.level().isClientSide()) {
                return;
            }
            CompoundTag compoundTag = player.getPersistentData();
            if (!player.getCooldowns().isOnCooldown(InitItems.blood_candle.asItem().getDefaultInstance())) {
                if (!compoundTag.getBooleanOr(hasOwnerBlood, false)) {
                    OwnerBlood EndComing = new OwnerBlood(EntityTs.owner_blood_.get(), player.level());
                    EndComing.setPos(player.position());
                    EndComing.setOwner(player);
                    EndComing.tame(player);
                    player.level().addFreshEntity(EndComing);
                    compoundTag.putBoolean(hasOwnerBlood, true);
                    player.getCooldowns().addCooldown(InitItems.blood_candle.asItem().getDefaultInstance(), 10);
                } else {
                    compoundTag.putBoolean(hasOwnerBlood, false);
                    player.getCooldowns().addCooldown(InitItems.blood_candle.asItem().getDefaultInstance(), 10);
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
    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("charm"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                2, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("charm").build())

        ), true);
    }
    @Override
    public KeyMapping theKeyMapping() {
        return Keys.KEY_MAPPING_LAZY_R;
    }

}

