package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.moonstoneitem.CommonItem;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import com.moonstone.moonstonemod.moonstoneitem.ThePain;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class BookEvt {
    public static final String lvl = "LVLBookEvt";
    public static final String exp = "expBookEvt";
    public static final int maxLvl = 3000;
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void BatteryName(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        List<Component> pTooltipComponents =event.getToolTip();
        if (stack.getTag()!=null) {
            int expValue = stack.getTag().getInt(exp);
            int lvlValue = stack.getTag().getInt(lvl);
            if (expValue!=0&& lvlValue!=0) {

                String baseKey = "item.at_a_book.tool.string.lvl.";
                int l = 1;
                if (expValue > 300) {
                    for (int i = 0; i < expValue; i += 300) {
                        l++;
                    }
                }
                // 添加到tooltip中
                pTooltipComponents.add(1,Component.literal(""));
                pTooltipComponents.add(1,Component.literal("")
                        .append(Component.translatable(baseKey + l)
                                .append(String.valueOf(lvlValue))
                                .append(Component.translatable("sword.moonstone.lvl"))
                                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF)))));

            }
        }
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event){
        if ((event.getSource().getEntity() instanceof Player player)){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getTag()!=null){
                            int expValue = stack.getTag().getInt(exp);
                            int lvlValue = stack.getTag().getInt(lvl);

                            if (expValue!=0&& lvlValue!=0) {
                                float l = 1f;
                                if (expValue > 300) {
                                    for (int x = 0; x < expValue; x += 300) {
                                        l++;
                                    }
                                }
                                l/=100f;//l是1%到10%
                                l/=10f;//l是0.1%~1%
                                if (l>1){
                                    l=1;
                                }
                                event.setAmount(event.getAmount()*(l+1));


                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event){
        if ((event.getEntity() instanceof Player player)){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getTag()!=null){
                            int expValue = stack.getTag().getInt(exp);
                            int lvlValue = stack.getTag().getInt(lvl);

                            if (expValue!=0&& lvlValue!=0) {
                                float l = 1f;
                                if (expValue > 300) {
                                    for (int x = 0; x < expValue; x += 300) {
                                        l++;
                                    }
                                }
                                l/=100f;//l是1%到10%
                                l/=5f;//l是0.5%到5%
                                if (l>1){
                                    l=1;
                                }
                                event.setAmount(event.getAmount()*(l+1));
                            }
                        }
                    }
                }
            });
        }
    }
    /**
     *
     * @param stack 是饰品
     * @param expLv 值在 0 ~ maxLvl 之间
     * @param lvls 值在 0 ~ maxLvl 之间
     */
    public static void addLvl(ItemStack stack,int expLv,int lvls){
        if (Config.SERVER.itemQuality.get()) {
            if (stack.getItem() instanceof ThePain
                    || stack.getItem() instanceof nightmare
                    || stack.getItem() instanceof Doom
                    || stack.getItem() instanceof CommonItem) {

                if (stack.getTag() == null) {
                    stack.getOrCreateTag();
                }
                stack.getTag().putInt(exp, expLv);
                if (stack.getTag() != null) {
                    String ss = lvl;
                    int l = lvls;
                    if (l > 300) {
                        for (int i = 0; i < maxLvl; i += 300) {
                            int to = i / 10;
                            if (to != 0) {
                                l = (l / to);
                                break;
                            }
                        }
                        if (l > 10 && l <= 20) {
                            l -= 10;
                        } else if (l > 20 && l <= 30) {
                            l -= 20;
                        } else if (l > 30 && l <= 40) {
                            l -= 30;
                        } else if (l > 40 && l <= 50) {
                            l -= 40;
                        } else if (l > 50 && l <= 60) {
                            l -= 50;
                        } else if (l > 60 && l <= 70) {
                            l -= 60;
                        } else if (l > 70 && l <= 80) {
                            l -= 70;
                        } else if (l > 80 && l <= 90) {
                            l -= 80;
                        } else if (l > 90 && l <= 100) {
                            l -= 90;
                        }
                        stack.getTag().putInt(ss, l);
                    } else {
                        stack.getTag().putInt(ss, l / 30);
                    }
                }
            }
        }
    }
}
