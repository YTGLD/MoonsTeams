package com.ytgld.moonstone.event;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.enttiy.AtSword;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.SwordOfTwelve;
import com.ytgld.moonstone.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextEvt {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void ItemTooltipEvent(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof Twelve){
            if (stack.is(Items.as_amout.get())||stack.is(Items.million.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.ectoplasmshild.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.redamout.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.magiceye.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.mblock.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.obsidianring.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.maxamout.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.7").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.fortunecrystal.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.8").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
//            if (stack.is(Items.dna.get())){
//                event.getToolTip().add(1,Component.translatable("moonstone.twelve.9").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
//            }
            if (stack.is(Items.bigwarcrystal.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.10").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.evilcandle.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.11").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(Items.killer.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.12").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
        }

    }

    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event){
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.twelve_sword.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.twelve_sword.get())) {
                    List<Integer> integers = new ArrayList<>();
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.getItem() instanceof Twelve) {
                                    integers.add(1);
                                }
                            }
                        }
                    });
                    int is = 0;
                    for (int ignored : integers) {
                        is++;
                    }

                   AtSword atSword = new AtSword(EntityTs.at_sword_entity.get(), player.level());

                    atSword.setOwner(player);
                    atSword.setPos(player.getX(), player.getY()+5, player.getZ());
                    atSword.setNoGravity(true);

                    player.level().addFreshEntity(atSword);
                    for (int i = 0; i < is; i++) {
                        SwordOfTwelve swordOfTwelve = new SwordOfTwelve(EntityTs.sword.get(), player.level());

                        double angle = (i * (2 * Math.PI / is));
                        double radius = is  ;
                        if (radius < 4) {
                            radius =4;
                        }
                        if (radius > 10) {
                            radius=10;
                        }
                        double x = player.getX() + radius * Math.cos(angle);
                        double y = player.getY()+5;
                        double z = player.getZ() + radius * Math.sin(angle);

                        swordOfTwelve.setOwner(player);
                        swordOfTwelve.setPos(x, y, z);
                        swordOfTwelve.getTags().add("SwordOfTwelveOFDamage");
                        player.level().addFreshEntity(swordOfTwelve);
                    }
                    player.getCooldowns().addCooldown(Items.twelve_sword.get(),1200);
                }
            }
        }
    }

    public interface Twelve{

    }
}
