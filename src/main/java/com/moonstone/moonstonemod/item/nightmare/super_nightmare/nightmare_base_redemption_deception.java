package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class nightmare_base_redemption_deception extends nightmare implements SuperNightmare{


    public static void LivingIncomingDamageEvent(LivingHurtEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_base_redemption_deception.get())){
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_redemption_deception.get())) {
                    if (event.getAmount() > player.getHealth()) {

                        player.heal(player.getMaxHealth()* (Config.SERVER.nightmare_base_redemption_deception.get()/100f));


                        player.getCooldowns().addCooldown(Items.nightmare_base_redemption_deception.get(), 1200);
                        player.invulnerableTime += (Config.SERVER.nightmare_base_redemption_deception_time.get()*20);
                        Vec3 playerPos = player.position().add(0, 0.75, 0);
                        float range =10;
                        List<LivingEntity> entities =
                                player.level().getEntitiesOfClass(LivingEntity.class,
                                        new AABB(playerPos.x - range,
                                                playerPos.y - range,
                                                playerPos.z - range,
                                                playerPos.x + range,
                                                playerPos.y + range,
                                                playerPos.z + range));

                        for (LivingEntity living : entities){
                            if (living instanceof Mob targeting){
                                if (targeting.getTarget()!=null && targeting.getTarget().is(player)){
                                    targeting.setTarget(null);
                                }
                            }
                        }
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
                        event.setAmount(0);
                    }
                }
            }
        }
    }


   
 @Override
    public void appendHoverText(ItemStack stack ,net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_redemption_deception.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_redemption_deception.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}
