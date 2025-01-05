package com.moonstone.moonstonemod.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class nightmarestone extends nightmare {


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                aDouble = AllEvent.EffectInstance(player);
                if (!player.level().isClientSide && player.tickCount % 20 == 0) {
                    player.getAttributes().addTransientAttributeModifiers(un_un_pla(player));
                    Collection<MobEffectInstance> collection = player.getActiveEffects();
                    for (MobEffectInstance mobEffectInstance : collection) {
                        MobEffect mobEffect = mobEffectInstance.getEffect();
                        if (!mobEffect.isBeneficial()) {
                            int lvl = mobEffectInstance.getAmplifier();
                            int time = mobEffectInstance.getDuration();
                            MobEffect effect = mobEffectInstance.getEffect();

                            player.addEffect(new MobEffectInstance(effect, time + 10, lvl));

                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));

        if (Screen.hasShiftDown()){
            tooltip.add(Component.translatable("item.nightmarestone.tool.string").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.translatable("item.nightmarestone.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.nightmarestone.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.nightmarestone.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmarestone.tool.string.7").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.nightmarestone.tool.string.8").withStyle(ChatFormatting.RED));

        }else {
            tooltip.add(Component.translatable("· [SHIFT]").withStyle(ChatFormatting.DARK_RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmarestone.tool.string.9").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmarestone.tool.string.10").withStyle(ChatFormatting.DARK_RED));
    }
    //c98b3962-3ea9-47f8-820d-134ce2691af0
    public static Multimap<Attribute, AttributeModifier> un_un_pla(Player player) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();


        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("c98b3962-3ea9-47f8-820d-134ce2691af0"), MoonStoneMod.MODID + "sojjmjmul",AllEvent. EffectInstance(player)/25, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("c98b3962-3ea9-47f8-820d-134ce2691af0"), MoonStoneMod.MODID + "soudfsdl", AllEvent. EffectInstance(player)/25, AttributeModifier.Operation.MULTIPLY_BASE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("c98b3962-3ea9-47f8-820d-134ce2691af0"), MoonStoneMod.MODID + "sojjmasdadjmul", AllEvent. EffectInstance(player)/25, AttributeModifier.Operation.MULTIPLY_BASE));

        return modifierMultimap;
    }
    public static double aDouble;
}
