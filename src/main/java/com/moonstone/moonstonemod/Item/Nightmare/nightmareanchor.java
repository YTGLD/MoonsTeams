package com.moonstone.moonstonemod.Item.Nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Item.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class nightmareanchor extends nightmare {
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> linkedHashMultimap = HashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);
        return linkedHashMultimap;
    }
    @Override
    public void releaseUsing(ItemStack stack, Level p_40668_, LivingEntity p_40669_, int p_40670_){
        int i = this.getUseDuration(stack) - p_40670_;

        if (p_40669_ instanceof Player player) {
            float f = BowItem.getPowerForTime(i);
            if (f == 1.0f) {
                if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                    if (player.level().dimension().toString().contains(stack.getOrCreateTag().getString("level"))) {
                        if (stack.getOrCreateTag().getDouble("x") != 0
                                && stack.getOrCreateTag().getDouble("y") != 0
                                && stack.getOrCreateTag().getDouble("z") != 0) {
                            player.teleportTo(stack.getOrCreateTag().getDouble("x"),
                                    stack.getOrCreateTag().getDouble("y"),
                                    stack.getOrCreateTag().getDouble("z"));
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.NEUTRAL, 1F, 1F);
                            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 2));
                            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 2));
                            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 2));
                            player.getCooldowns().addCooldown(stack.getItem(), 300);
                        }
                    }
                }
            }
        }
    }
    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
        InteractionResultHolder<ItemStack> stackInteractionResultHolder = super.use(level, player, p_41434_);

        player.startUsingItem(p_41434_);
        return stackInteractionResultHolder;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        if (Screen.hasShiftDown()){
            tooltip.add(Component.translatable("item.nightmareanchor.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.nightmareanchor.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmareanchor.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.nightmareanchor.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmareanchor.tool.string.4").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.nightmareanchor.tool.string.5").withStyle(ChatFormatting.DARK_RED));

        }else {
            tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmareanchor.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmareanchor.tool.string.7").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
    }
}
