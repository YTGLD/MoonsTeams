package com.ytgld.moonstone.item.ms.necora;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;
import java.util.Set;

public class Necora extends TheNecora {

    public Necora(Properties properties) {
        super(properties);
    }

    @Override
    public Set<Item> canUSe() {
        return Set.of(Items.ambush.asItem(),
                Items.atpoverdose.asItem(),
                Items.autolytic.asItem(),
                Items.fermentation.asItem(),
                Items.putrefactive.asItem(),
                Items.regenerative.asItem()
        );
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        var att = super.getAttributeModifiers(stack, livingEntity);
        modifyAttribute(stack,Items.regenerative.asItem(), AttReg.heal,new AttributeModifier(
                id(stack),0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),att);
        modifyAttribute(stack,Items.autolytic.asItem(), NeoForgeMod.SWIM_SPEED,new AttributeModifier(
                id(stack),0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),att);
        modifyAttribute(stack,Items.atpoverdose.asItem(), Attributes.MAX_HEALTH,new AttributeModifier(
                id(stack),4, AttributeModifier.Operation.ADD_VALUE
        ),att);
        modifyAttribute(stack,Items.fermentation.asItem(), AttReg.cit,new AttributeModifier(
                id(stack),0.13, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),att);
        modifyAttribute(stack,Items.putrefactive.asItem(), Attributes.ARMOR,new AttributeModifier(
                id(stack),0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),att);
        modifyAttribute(stack,Items.ambush.asItem(), Attributes.LUCK,new AttributeModifier(
                id(stack),2, AttributeModifier.Operation.ADD_VALUE
        ),att);
        return att;
    }

    @Override
    public int maxSize() {
        return 4;
    }

    public static void necora(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.necora.asItem())) {
                if (NecoraHandler.has(player, Items.putrefactive.asItem())) {
                    if (event.getItem().is(net.minecraft.world.item.Items.ROTTEN_FLESH)) {
                        if (!Handler.hascurio(player, Items.putrefactive.get())) {
                            player.heal(player.getMaxHealth() / 20);
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 200, 0));
                        } else {
                            player.heal(player.getMaxHealth() / 15);
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
                            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 300, 1));
                        }
                    }
                }
            }
        }
    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(Player player, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        double acc = 0.8;
        if (NecoraHandler.has(player, Items.autolytic.get())) {
            acc = 0;
        }
        multimap.put(Attributes.WATER_MOVEMENT_EFFICIENCY, new AttributeModifier(
                identifier(),
                -acc,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                identifier(),
                3,
                AttributeModifier.Operation.ADD_VALUE));


        return multimap;
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().addTransientAttributeModifiers(Head(player, stack));

            if (player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() &&
                    (player.level().canSeeSky(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ())))) {
                player.setRemainingFireTicks(3);
            }
        }
    }


    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(Head(player, stack));
        }
    }

    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("dna"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                2, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("dna").build()),
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("necora"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                2, AttributeModifier.Operation.ADD_VALUE)
                        , SlotTypePredicate.builder().withId("necora").build())
        ), true);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.necora.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.2").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.3").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.4").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.5").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.necora.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.7").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.8").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.9").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.10").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.necora.tool.string.11").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
        } else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("-[SHIFT]").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
            tooltip.add(Component.translatable("item.necora.tool.string.12").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.13").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.14").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.necora.tool.string.15").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable(""));
        }
    }
}
