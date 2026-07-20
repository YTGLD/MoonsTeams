package com.ytgld.moonstone.item.si.fall;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.event.NewEvent;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import com.ytgld.moonstone.other.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.common.slot.SlotTypePredicate;

import java.util.List;

public class DivineFallRing extends FallItem {

    public static final String uDead = "undead";

    public DivineFallRing(Properties properties) {
        super(properties);
    }

    public static void exp(MobEffectEvent.Applicable event) {
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.the_divine_fall_ring.get())) {
                if (event.getEffectInstance().getEffect().is(MobEffects.BLINDNESS) ||
                        event.getEffectInstance().getEffect().is(MobEffects.DARKNESS)) {
                    event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
                }
            }
        }
    }

    public static void exp(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() instanceof Player) {
            if (SIHandler.hascurio(event.getAttackingPlayer(), Items.the_divine_fall_ring.get())) {
                event.setDroppedExperience(event.getDroppedExperience() * 2);
            }
        }
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 2;
    }


    @Override

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity entity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 10, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 10, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 10, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(AttReg.heal, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId), 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifierMultimap;
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            if (slotContext.entity().tickCount >= 20) {
            } else {
                slotContext.entity().invulnerableTime = 200;
            }
        }
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag, new CompoundTag());
        }
        if (slotContext.entity() instanceof Player player) {

            if (!player.level().isClientSide()) {
                float lv = player.getHealth() / player.getMaxHealth();
                if (lv > 1) {
                    lv = 1;
                }
                lv *= 100;
                int now = (int) (100 - (lv));
                if (stack.get(DataReg.tag) == null) {
                    stack.set(DataReg.tag, new CompoundTag());

                }
                if (stack.get(DataReg.tag) != null) {
                    stack.get(DataReg.tag).putInt(uDead, now);
                }

                player.getAttributes().addTransientAttributeModifiers(ad(stack));
            }
        }
    }

    @Override
    public void onUnequipUse(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        slotContext.entity().getAttributes().removeAttributeModifiers(ad(stack));
    }

    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.get(DataReg.tag) != null) {
            int lvl = stack.get(DataReg.tag).getIntOr(uDead, 0);
            float heal = 0.85f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;


            modifiers.put(AttReg.heal, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                    heal, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                    speed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                    damage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                    attSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                    armor, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


        }
        return modifiers;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        String tring1 = "item.the_divine_fall_ring.tool.string.1";
        String tring2 = "item.the_divine_fall_ring.tool.string.2";
        String tring3 = "item.the_divine_fall_ring.tool.string.3";
        String tring4 = "item.the_divine_fall_ring.tool.string.4";
        String tring5 = "item.the_divine_fall_ring.tool.string.5";
        String tring6 = "item.the_divine_fall_ring.tool.string.6";
        String tring7 = "item.the_divine_fall_ring.tool.string.7";

        addTip(tooltipComponents, tring1);
        addTip(tooltipComponents, tring2);
        addTip(tooltipComponents, tring3);
        addTip(tooltipComponents, tring4);
        addTip(tooltipComponents, tring5);
        addTip(tooltipComponents, tring6);
        addTip(tooltipComponents, tring7);

        tooltipComponents.add(Component.translatable("item.immortal.tool.string.5").withStyle(ChatFormatting.RED));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        MutableComponent co = component.copy();
        co.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF0000)));
        return co;
    }
    @Override
    public CurioAttributeModifiers getDefaultCurioAttributeModifiers(ItemStack stack) {
        return new CurioAttributeModifiers(List.of(
                new CurioAttributeModifiers.Entry(SlotAttribute.getOrCreate("nightmare"),
                        new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID, this.descriptionId),
                                -2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                        , SlotTypePredicate.builder().withId("nightmare").build())
        ), true);
    }
    public void addTip(List<Component> tooltipComponents, String Z) {
        int red = 255;
        int purple = 255;
        int g = (int) (255 * Math.sin(NewEvent.time / 200f));
        g /= 2;
        if (g < 0) {
            g = -g;
        }
        tooltipComponents.add(Component.translatable(Z).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255, red, g, purple)))));
    }
}