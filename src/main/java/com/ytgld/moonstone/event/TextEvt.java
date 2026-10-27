package com.ytgld.moonstone.event;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.enttiy.AtSword;
import com.ytgld.moonstone.enttiy.EntityTs;
import com.ytgld.moonstone.enttiy.SwordOfTwelve;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.other.Light;
import com.ytgld.moonstone.utils.AddUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
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
            if (stack.is(InitItems.as_amout.get())||stack.is(InitItems.million.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.ectoplasmshild.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.redamout.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.magiceye.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.mblock.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.obsidianring.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.6").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.maxamout.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.7").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.fortunecrystal.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.8").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
//            if (stack.is(Items.dna.get())){
//                event.getToolTip().add(1,Component.translatable("moonstone.twelve.9").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
//            }
            if (stack.is(InitItems.bigwarcrystal.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.10").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.evilcandle.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.11").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
            if (stack.is(InitItems.killer.get())){
                event.getToolTip().add(1,Component.translatable("moonstone.twelve.12").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB0E2FF))));
            }
        }

    }

    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event){
        if (event.getSource().getDirectEntity() instanceof Player player) {
            if (Handler.hascurio(player, InitItems.twelve_sword.get())) {
                if (!player.getCooldowns().isOnCooldown(InitItems.twelve_sword.get().getDefaultInstance())) {
                    int is = 12;
                    AtSword atSword = new AtSword(EntityTs.at_sword_entity.get(), player.level());

                    atSword.setOwner(player);
                    atSword.setPos(player.getX(), player.getY()+8, player.getZ());
                    atSword.setNoGravity(true);

                    player.level().addFreshEntity(atSword);
                    for (int i = 0; i < is; i++) {
                        SwordOfTwelve swordOfTwelve = new SwordOfTwelve(EntityTs.sword.get(), player.level());

                        double angle = (i * (2 * Math.PI / is));
                        double radius = is + 4 ;

                        double x = player.getX() + radius * Math.cos(angle);
                        double y = player.getY()+5;
                        double z = player.getZ() + radius * Math.sin(angle);
                        int s = 4;
                        int ccRed = 0;
                        int ccGreen = 0;
                        if (i % 4 == 0) {
                            s = 6;
                            ccRed += 75;
                            ccGreen += 100;
                        }
                        AddUtil.addWrathHeaven(player,new Vec3(x,player.getY() + 0.1f,z),
                                Light.ARGB.color(255,
                                        25 + ccRed,25 + ccGreen,
                                        255),s,false);


                        swordOfTwelve.setOwner(player);
                        swordOfTwelve.setPos(x, y, z);
                        swordOfTwelve.entityTags().add("SwordOfTwelveOFDamage");
                        player.level().addFreshEntity(swordOfTwelve);
                    }
                    player.getCooldowns().addCooldown(InitItems.twelve_sword.get().getDefaultInstance(),1200);
                }
            }
        }
    }

    public interface Twelve{

    }
}
