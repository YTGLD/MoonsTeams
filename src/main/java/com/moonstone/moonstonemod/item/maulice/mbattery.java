package com.moonstone.moonstonemod.item.maulice;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.moonstoneitem.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class mbattery extends MLS {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            Vec3 playerPos = player.position().add(0, 0.75, 0);
            int range = 8;
            List<Zombie> entities = player.level().getEntitiesOfClass(Zombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Zombie zombie : entities) {
                zombie.goalSelector.addGoal(1, new AvoidEntityGoal<>(zombie, Player.class, 6.0F, 1.0D, 1.2D));
            }
        }
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 2;
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return 2;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID+":mbattery", 1, AttributeModifier.Operation.ADDITION));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID+":mbattery", 0.12, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID+":mbattery", 0.18, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.mbattery.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mbattery.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mbattery.tool.string.2").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(Component.translatable("item.mbattery.tool.string.3").withStyle(ChatFormatting.DARK_GREEN));

    }

}
