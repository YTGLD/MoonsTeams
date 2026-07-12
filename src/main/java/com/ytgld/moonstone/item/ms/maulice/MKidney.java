package com.ytgld.moonstone.item.ms.maulice;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

public class MKidney extends MLS {
    public MKidney(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Handler.stackCreateTag(stack);
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("charm"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                1, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("charm").build())
        ), true);
    }
    public static void brainLHurt(LivingDamageEvent.Pre event) {

        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.mkidney.get())) {
                int Kidney = player.getPersistentData().getIntOr("mkidney",0);
                if (!player.getCooldowns().isOnCooldown(Items.mkidney.get().getDefaultInstance())) {
                    if (Mth.nextInt(RandomSource.create(), 0, 100) < Kidney) {
                        player.getPersistentData().getIntOr("mkidney",Kidney / 2);
                        event.setNewDamage(0);
                    } else {
                        player.getPersistentData().getIntOr("mkidney",100);

                        event.setNewDamage(event.getNewDamage() + player.getMaxHealth() / 3);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_DEACTIVATE, SoundSource.NEUTRAL, 1, 1);
                        player.getCooldowns().addCooldown(Items.mkidney.get().getDefaultInstance(), 200);
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);

        tooltip.add(Component.translatable("item.mkidney.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mkidney.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mkidney.tool.string.2").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mkidney.tool.string.3").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mkidney.tool.string.4").withStyle(ChatFormatting.DARK_GREEN));

    }
}
