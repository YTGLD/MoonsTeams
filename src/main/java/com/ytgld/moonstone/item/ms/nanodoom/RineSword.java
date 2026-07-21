package com.ytgld.moonstone.item.ms.nanodoom;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.FlySword;
import com.ytgld.moonstone.item.ms.Doom;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

import static com.ytgld.moonstone.item.Items.doomswoud;

public class RineSword extends Doom {
    public static String canFlySword = "canFlySword";

    public RineSword(Properties properties) {
        super(properties);
    }

    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack Other, Slot p_150744_, ClickAction p_150745_, Player p_150746_, SlotAccess p_150747_) {
        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(p_150746_)) {
            if (Other.isEmpty()) {
                if (me.get(DataReg.tag) == null) {
                    me.set(DataReg.tag, new CompoundTag());
                }
                CompoundTag tag = me.get(DataReg.tag);
                boolean canFlySword = tag.getBooleanOr(RineSword.canFlySword, false); // 假设"canFlySword"是一个字符串常量
                tag.putBoolean(RineSword.canFlySword, !canFlySword);
                return true;
            }
        }
        return false;
    }

    public static void suddenrainLLivingHurtEvent(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, doomswoud.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(doomswoud.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBooleanOr(SevenSword.canFlySword, false)) {
                                        if (!player.getCooldowns().isOnCooldown(doomswoud.get().getDefaultInstance())) {
                                            for (int p = 0; p < 2; p++) {
                                                float s = (float) Math.sin(p);
                                                if (s <= 0) {
                                                    s = 0.12f;
                                                }
                                                FlySword item = new FlySword(EntityTs.flysword.get(), player.level());
                                                item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                                                item.setOwner(player);
                                                item.setTarget(event.getEntity());
                                                item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f), s / 1.5f, Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f));
                                                player.level().addFreshEntity(item);
                                                player.getCooldowns().addCooldown(doomswoud.get().getDefaultInstance(), 40);
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

    public static void suddenrainLivingDeathEvent(LivingDeathEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player, doomswoud.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(doomswoud.get())) {
                                if (!player.getCooldowns().isOnCooldown(doomswoud.get().getDefaultInstance())) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (!stack.get(DataReg.tag).getBooleanOr(SevenSword.canFlySword, false)) {

                                            for (int p = 0; p < 4; p++) {
                                                float s = (float) Math.sin(p);
                                                if (s <= 0) {
                                                    s = 0.12f;
                                                }
                                                FlySword item = new FlySword(EntityTs.flysword.get(), player.level());
                                                item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                                                item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f), s / 1.5f, Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f));
                                                item.setOwner(player);
                                                item.setTarget(event.getEntity());
                                                player.level().addFreshEntity(item);
                                                player.getCooldowns().addCooldown(doomswoud.get().getDefaultInstance(), 50);
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


    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.doomswoud.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.doomswoud.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.doomswoud.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.moonstone.tool.string.sword").withStyle(ChatFormatting.GOLD));
        if (stack.get(DataReg.tag) != null) {
            if (!stack.get(DataReg.tag).getBooleanOr(canFlySword, false)) {
                tooltip.add(Component.translatable("item.moonstone.tooltips.off").withStyle(ChatFormatting.GOLD));
            } else {
                tooltip.add(Component.translatable("item.moonstone.tooltips.on").withStyle(ChatFormatting.GOLD));
            }
        } else {
            tooltip.add(Component.translatable("item.moonstone.tooltips.off").withStyle(ChatFormatting.GOLD));
        }
    }
}
