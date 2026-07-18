package com.ytgld.moonstone.item.si.nightmare.other;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.AttReg;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class FlagOfProtest extends NightmareSmall {
    public static final String dieSizeKy = "DefyLifeDie";
    public static final String canUse = "DefyLifeCanUse";

    public FlagOfProtest(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem) {
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (clickAction.equals(ClickAction.SECONDARY)) {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean(canUse,true);
            if (compoundTag == null) {
                stack.set(DataReg.tag,tag);
            }
            if (compoundTag != null) {
                compoundTag.putBoolean(canUse, !compoundTag.getBooleanOr(canUse, false));
                return true;
            }
        }
        return super.overrideOtherStackedOnMe(stack, other, slot, clickAction, player, carriedItem);
    }
    public static void PlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event){
        if (event.getEntity() instanceof Player player) {
            if (SIHandler.hascurio(player, Items.flag_of_protest.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.flag_of_protest.asItem())) {
                                CompoundTag compoundTag = stack.get(DataReg.tag);
                                if (compoundTag != null) {
                                    if (!player.getCooldowns().isOnCooldown(Items.flag_of_protest.asItem().getDefaultInstance())) {
                                        updateTga(stack);
                                        if (player.level() instanceof ServerLevel level) {
                                            if (compoundTag.getBooleanOr(canUse, false)) {
                                                player.getLastDeathLocation().ifPresent((globalPos -> {
                                                    player.teleportTo(level, globalPos.pos().getX(), globalPos.pos().getY(), globalPos.pos().getZ(), Set.of(), 0, 0, false);
                                                }));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private static void updateTga(ItemStack stack){
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag == null) {
            stack.set(DataReg.tag,new CompoundTag());
        }
        if (compoundTag != null) {
            if (compoundTag.getIntOr(dieSizeKy, 0) < 10) {
                compoundTag.putInt(dieSizeKy,compoundTag.getIntOr(dieSizeKy, 0) + 1);
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack, LivingEntity livingEntity) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();

        float value = 0;
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null && livingEntity instanceof Player player) {
            value = (compoundTag.getIntOr(dieSizeKy,0) + 1) / 20f;
            if (!player.getCooldowns().isOnCooldown(stack) && player.tickCount > 300) {
                if (compoundTag.getIntOr(dieSizeKy,0) > 0) {
                    compoundTag.putInt(dieSizeKy, compoundTag.getIntOr(dieSizeKy, 0) - 1);
                    player.getCooldowns().addCooldown(stack,300);
                }
            }
        }

        modifiers.put(AttReg.heal, new AttributeModifier(identifier(),
                value, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(identifier(),
                value, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(identifier(),
                value, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(identifier(),
                value, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        return modifiers;
    }

    @Override
    public void text(ItemStack stack, Consumer<Component> tooltipComponents, TooltipFlag flag) {
        super.text(stack, tooltipComponents, flag);
        tooltipComponents.accept(Component.translatable("item.moonstone.flag_of_protest.string.1").withStyle(Style.EMPTY.withColor(0xffff0000)));
        tooltipComponents.accept(Component.translatable("item.moonstone.flag_of_protest.string.2").withStyle(Style.EMPTY.withColor(0xffff0000)));
        tooltipComponents.accept(Component.translatable("item.moonstone.flag_of_protest.string.3").withStyle(Style.EMPTY.withColor(0xffff0000)));
        tooltipComponents.accept(Component.literal(""));
        if (stack.get(DataReg.tag) !=null) {
            if (stack.get(DataReg.tag).getBooleanOr(canUse,false)) {
                tooltipComponents.accept(Component.translatable("item.moonstone.flag_of_protest.string.4").withStyle(Style.EMPTY.withColor(0xffff0000)));
            }
        }
    }
}
