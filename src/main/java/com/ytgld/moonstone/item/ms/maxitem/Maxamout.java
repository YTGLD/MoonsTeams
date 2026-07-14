package com.ytgld.moonstone.item.ms.maxitem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.event.TextEvt;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Maxamout extends UnCommonItem implements TextEvt.Twelve {
    public Maxamout(Properties properties) {
        super(properties);
    }

    public static void maxamout(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.maxamout.get())) {
                if (event.getSource().getEntity() != null) {
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        if (event.getSource().getEntity() != null) {
                            float s = 0.2f;
                            if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                                s *= 5f;
                            }

                            living.hurt(living.damageSources().magic(), event.getNewDamage() * s);
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);
                        }
                    }
                }

                event.setNewDamage(event.getNewDamage() * 0.85f);
                float s = 1;
                if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                    s += 1;
                }
                if (Mth.nextInt(RandomSource.create(), 1, (int) (5 / s)) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20, (int) (2 + s)));
                }
            }
        }
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.maxamout.get())) {
                float w = 1;
                if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                    w += 1;
                }
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, (int) (0 + w)));
                float s = event.getNewDamage() / 20;
                if (s > 5) {
                    s = 5;
                }
                player.heal(s);
                float ss = 1;
                if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                    ss += 1;
                }
                if (Mth.nextInt(RandomSource.create(), 1, (int) (12 / ss)) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 60, (int) (0 + ss)));
                    event.getEntity().knockback(0.2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.YELLOW_WOOL.defaultBlockState()));
                }
            }
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (player.hasEffect(MobEffects.MINING_FATIGUE)) {
                player.removeEffect(MobEffects.MINING_FATIGUE);
            }
        }
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().addTransientAttributeModifiers(swim(player, stack));
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(swim(player, stack));
        }
        if (slotContext.entity() instanceof Player player) {
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {

            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.maxamout.tool.string").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.1").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.maxamout.tool.string.2").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.3").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.maxamout.tool.string.6").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.7").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.maxamout.tool.string.8").withStyle(ChatFormatting.GOLD));
        } else {
            tooltip.add(Component.translatable("SHIFT").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));


        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> swim(Player player, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(identifier(), 0.75, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}


