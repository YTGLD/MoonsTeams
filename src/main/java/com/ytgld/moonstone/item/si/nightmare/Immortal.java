package com.ytgld.moonstone.item.si.nightmare;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.effect.Effects;
import com.ytgld.moonstone.item.Items;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Immortal extends NightmareSmall{

    public Immortal(Properties properties) {
        super(properties);
    }

    public static void hEvt(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (event.getEntity() instanceof Player player){
                int lvl = Mth.nextInt(RandomSource.create(),1,100);
                if (SIHandler.hascurio(player, Items.immortal.get())){
                    if (lvl<=80){
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 1F, 1F);
                        if (living.getHealth()<=living.getMaxHealth()*0.7f){
                            living.hurt(living.damageSources().dryOut(),event.getNewDamage()*0.5f);
                            event.setNewDamage(0);
                        }else {
                            living.invulnerableTime = 0;
                            living.hurt(living.damageSources().dryOut(),event.getNewDamage()*1.5f);
                            event.setNewDamage(0);
                        }
                    }
                }
            }
        }
    }
    public static void livDead(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (event.getEntity() instanceof Player player){
                if (SIHandler.hascurio(player, Items.immortal.get())){
                    living.hurt(living.damageSources().dryOut(),living.getHealth()*0.2f);
                    living.addEffect(new MobEffectInstance(Effects.dead,200,9));
                }
            }
        }
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            Vec3 playerPos = player.position().add(0, 0.75, 0);
            int range = 8;
            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

            for (LivingEntity living : entities){
                if (!living.is(player)){
                    if (player.tickCount%100==1){
                        living.addEffect(new MobEffectInstance(Effects.dead,600,0));

                        if (living.getEffect(Effects.dead)!=null){
                            if (living.getEffect(Effects.dead).getAmplifier()<5) {
                                living.addEffect(new MobEffectInstance(Effects.dead, 600, living.getEffect(Effects.dead).getAmplifier() + 1));
                            }else {
                                living.addEffect(new MobEffectInstance(Effects.dead, 600, 5));
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
       if (flags.hasShiftDown()) {
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string").withStyle(ChatFormatting.RED));
           tooltip.add(Component.translatable("item.immortal.tool.string.1").withStyle(ChatFormatting.RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.2").withStyle(ChatFormatting.RED));
           tooltip.add(Component.translatable("item.immortal.tool.string.3").withStyle(ChatFormatting.RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.4").withStyle(ChatFormatting.RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.5").withStyle(ChatFormatting.RED));
       }else {
           tooltip.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
           tooltip.add(Component.translatable("item.immortal.tool.string.7").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
           tooltip.add(Component.translatable("item.immortal.tool.string.8").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
       }
    }
}
