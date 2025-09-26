package com.moonstone.moonstonemod.item;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import com.moonstone.moonstonemod.moonstoneitem.UnCommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class universe extends UnCommonItem implements Die {
    public static final String doAsUniverse = "doAsUniverse";
    public static final int universeSize = 5;


    public static final String eTime =
            "ETime";//持续时间
    public static final String noAdd =
            "noAdd";//再这个状态下无法增加伤害

    public static void attack(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.universe.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.universe.get())){
                                CompoundTag compoundTag = player.getPersistentData();
                                compoundTag.putInt(eTime, Config.SERVER.universe.get());

                                if (!compoundTag.getBoolean(noAdd)) {
                                    compoundTag.putString(event.getSource().type() +"moonstone_universe", event.getSource().type() +"moonstone_universe");
                                    float damage = (Config.SERVER.universe2.get() / 100f);

                                    List<Integer> integers = new ArrayList<>();
                                    for (String string : compoundTag.getAllKeys()){
                                        if (string.contains("moonstone_universe")) {
                                            integers.add(1);
                                        }
                                    }
                                    int l = 1;
                                    for (int ignored : integers){
                                        l++;
                                    }
                                    damage *= l;
                                    event.setAmount(event.getAmount() * (1 + damage));
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.universe.tool.string").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.universe.tool.string.1").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.universe.tool.string.2").withStyle(ChatFormatting.GOLD));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if (!slotContext.entity().level().isClientSide) {
            if (stack.getTag() == null) {
                stack.getOrCreateTag();
            }
            if (slotContext.entity() instanceof Player player) {
                CompoundTag compoundTag = player.getPersistentData();
                if (compoundTag.getInt(eTime) > 0) {
                    compoundTag.putInt(eTime, compoundTag.getInt(eTime) - 1);
                    compoundTag.putBoolean(noAdd, false);
                } else {
                    compoundTag.putBoolean(noAdd, true);
                    List<String> keysToRemove = new ArrayList<>(compoundTag.getAllKeys());
                    for (String key : keysToRemove) {
                        if (!key.equals(eTime) && !key.equals(noAdd)) {
                            if (key.contains("moonstone_universe")) {
                                compoundTag.remove(key);
                            }
                        }
                    }
                }
            }
        }
    }
}
