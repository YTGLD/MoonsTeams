package com.moonstone.moonstonemod.Event;

import com.google.common.collect.Lists;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.Init.Items;
import com.moonstone.moonstonemod.Item.IDoom;
import com.moonstone.moonstonemod.Item.IEctoplasm;
import com.moonstone.moonstonemod.Item.INightmare;
import com.moonstone.moonstonemod.Item.MLS;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AllEvent {
    private int shield = 1;
    private int Kidney = 100;
    private float clientSideAttackTime = 0;
    @SubscribeEvent
    public void KnockBack_nightmarestone(LivingKnockBackEvent  event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setStrength(event.getStrength() * (1 - EffectInstance(player) / 14));
            }

            if (Handler.hascurio(player,Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    if (stack.getTag()!= null){
                                        float dam =stack.getTag().getInt("kok");
                                        dam /= 100;
                                        event.setStrength(event.getStrength() * (1 - dam));
                                    }
                                }
                            }
                        }
                    }
                });
            }

        }
    }

    @SubscribeEvent
    public void Heal_nightmarestone(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setAmount(event.getAmount() * (1 + EffectInstance(player) / 14));
            }
        }

    }

    @SubscribeEvent
    public void exp_nightmarestone(LivingExperienceDropEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setDroppedExperience((int) (event.getDroppedExperience() * (1 + EffectInstance(player) / 10)));
            }
        }
    }
    @SubscribeEvent
    public void BreakSpeed_nightmarestone(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() != null){
            Player player = event.getEntity();
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setNewSpeed(event.getNewSpeed() * (1 + EffectInstance(player) / 10));
            }
        }
    }
    @SubscribeEvent
    public void nightmareeye(LivingHurtEvent event) {

        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                event.setAmount(event.getAmount() * 1.33f);
            }

        }
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmareeye.get())) {
                event.setAmount(event.getAmount() * 0.75f);
            }
        }

    }

    @SubscribeEvent
    public void nightmareanchor(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, com.moonstone.moonstonemod.Init.Items.nightmareeye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmareanchor.get())) {
                                if (Handler.hascurio(player, Items.nightmareanchor.get())) {

                                    double playerX = player.getX();
                                    double playerY = player.getY();
                                    double playerZ = player.getZ();

                                    stack.getOrCreateTag().putDouble("x", playerX);
                                    stack.getOrCreateTag().putDouble("y", playerY);
                                    stack.getOrCreateTag().putDouble("z", playerZ);


                                    stack.getOrCreateTag().putString("level", player.level().dimension().toString());
                                }
                            }
                        }
                    }
                });

            }
        }
    }
    @SubscribeEvent
    public void LivingKnockBackEvent(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.mring.get())){
                event.setStrength(event.getStrength() * 2);
            }
        }
    }
    @SubscribeEvent
    public   void LivingHealEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.mring.get())){
                event.setAmount(event.getAmount() * 1.4f);
            }
        }

    }
    @SubscribeEvent
    public void LivingExperienceDropEvent(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (Handler.hascurio(player, Items.morb.get())){
            event.setDroppedExperience(((int) ((event.getDroppedExperience()*1.5))) + 1);
        }
    }
    @SubscribeEvent
    public void nightmaretreasure_LivingDropsEvent(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                if (Handler.hascurio(player,  Items.nightmaretreasure.get())) {
                    Random random = new Random();
                    int s = 33;
                    s += (int) player.getLuck();

                    if (s > 66){
                        s = 66;
                    }
                    if(
                            random.nextInt(100) <= s
                    ) {
                        Collection<ItemEntity> drop = event.getDrops();
                        for (ItemEntity entity : drop) {
                            ItemStack stack = entity.getItem();
                            if (stack.getMaxStackSize() != 1) {
                                stack.setCount(stack.getCount() * 3);
                                entity.setItem(stack);
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.thedoomstone.get())) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DARKNESS ,120 ,1));

                Vec3 playerPos = event.getEntity().position();
                int range = 12;
                List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));


                for (LivingEntity livingentity : entities) {
                    if (livingentity.hasEffect(MobEffects.DARKNESS )) {
                        if (!livingentity.is(player)) {
                            {
                                if (this.clientSideAttackTime < 80) {
                                    ++this.clientSideAttackTime;
                                }
                                double d5 = this.getAttackAnimationScale(0.0F);
                                double d0 = livingentity.getX() - event.getEntity().getX();
                                double d1 = livingentity.getY(0.5D) - event.getEntity().getEyeY();
                                double d2 = livingentity.getZ() - event.getEntity().getZ();
                                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                                d0 /= d3;
                                d1 /= d3;
                                d2 /= d3;
                                double d4 = event.getEntity().getRandom().nextDouble();
                                while (d4 < d3) {
                                    d4 += 1.8D - d5 + event.getEntity().getRandom().nextDouble() * (1.7D - d5);

                                    if (event.getEntity().level() instanceof ServerLevel serverLevel) {
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                    }
                                }
                                livingentity.level().playSound(null, livingentity.getX(), livingentity.getY(), livingentity.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 0.22f, 0.22f);
                                livingentity.hurt(livingentity.damageSources().magic(), 4 + livingentity.getMaxHealth() / 25);
                                livingentity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1));
                                livingentity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                                livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));

                            }
                        }
                    }
                }
            }

            if (Handler.hascurio(player, Items.magiceye.get())) {
                Vec3 playerPos = event.getEntity().position().add(0, 0.75, 0);
                int range = 4;
                List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (LivingEntity living : entities) {
                    if (!living.is(player)) {
                        if (!living.is(event.getEntity())) {
                            living.hurt(living.damageSources().magic(), event.getAmount() / 2);
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                if (Handler.hascurio(player, Items.nightmaretreasure.get())) {
                    event.setAmount(event.getAmount() / 2);
                }
            }
            if (Handler.hascurio(player, Items.nightmarestone.get())) {
                int s = Mth.nextInt(RandomSource.create(), 1, 5);
                if (s == 1)
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0));
                if (s == 2)
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                if (s == 3)
                    player.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 100, 0));
                if (s == 4)
                    player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                if (s == 5)
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 2));
                if (s == 6)
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
            }


            if (Handler.hascurio(player, Items.mshell.get())) {
                event.setAmount(event.getAmount() * 0.9f);
            }
            if (Handler.hascurio(player, Items.mbattery.get())) {
                if (event.getEntity() instanceof Zombie) {
                    event.setAmount(event.getAmount() * 1.5f);
                }
            }
            if (Handler.hascurio(player, Items.mkidney.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.mkidney.get())) {
                    if (Mth.nextInt(RandomSource.create(), 0, 100) < Kidney) {
                        Kidney /= 2;
                        event.setAmount(0);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_HURT, SoundSource.NEUTRAL, 1, 1);
                    } else {
                        Kidney = 100;

                        event.setAmount(event.getAmount() + player.getMaxHealth() / 3);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 1, 1);
                        player.getCooldowns().addCooldown(Items.mkidney.get(), 200);
                    }
                }
            }

            if (Handler.hascurio(player, Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    if (stack.getTag() != null) {
                                        float dam = stack.getTag().getInt("damage");
                                        dam /= 100;
                                        event.setAmount(event.getAmount() * (1 + dam));
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
        /**
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    if (stack.getTag()!= null){
                                        float dam =stack.getTag().getInt("regs");
                                        dam /= 100;
                                        event.setAmount(event.getAmount() * (1 + dam));
                                    }
                                }
                            }
                        }
                    }
                });
            }
            if (Handler.hascurio(player, Items.mshell.get())) {
                if (event.getSource().getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()) {
                        event.setAmount(event.getAmount() * 0.75f);
                    }
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmapple.get())) {
                if (event.getSource() != null) {
                    Entity source = event.getSource().getEntity();
                    if (source instanceof LivingEntity) {
                        Vec3 playerPos = player.position();
                        int range = 8;
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(
                                playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));
                        for (LivingEntity livingEntity : entities) {
                            if (!livingEntity.is(player)) {
                                livingEntity.hurt(livingEntity.damageSources().magic(), livingEntity.getMaxHealth() / 50);
                            }
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmhorseshoe.get())) {
                if (event.getSource().is(DamageTypes.FALL)) {
                    event.setAmount(event.getAmount() / 10);
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmshild.get())) {
                if (event.getSource().is(DamageTypes.EXPLOSION)) {
                    event.setAmount(event.getAmount() * 0.7F);
                }
                shield += 1;
                if (shield >= 5) {
                    event.setAmount(0);
                    shield = 0;
                }
            }
        }
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void RenderTooltipEven4t(RenderTooltipEvent.Color tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();
        if (stack.getItem() instanceof IEctoplasm) {
            tooltipEvent.setBorderStart(0xFF87CEFA);
            tooltipEvent.setBorderEnd(0xFFF8F8FF);
        }
        if (stack.getItem() instanceof MLS) {
            tooltipEvent.setBorderStart(0xFF006400);
            tooltipEvent.setBorderEnd(0xFF006400);
        }
        if (stack.getItem() instanceof INightmare) {
            tooltipEvent.setBorderStart(0xFF800000);
            tooltipEvent.setBorderEnd(0xFF800080);
        }
        if (stack.getItem() instanceof IDoom) {
            tooltipEvent.setBorderStart(0xFF83DEFC);
            tooltipEvent.setBorderEnd(0xFF0296FE);
        }
    }

    public static float EffectInstance(Player player) {
        float size = 0;
        List<Integer> Int = Lists.newArrayList();
        Collection<MobEffectInstance> collection = player.getActiveEffects();
        for (MobEffectInstance mobEffectInstance : collection){
            MobEffect mobEffect = mobEffectInstance.getEffect();
            if (!mobEffect.isBeneficial()){
                Int.add(1);
            }
        }
        for (Integer i : Int){
            size += 1;
        }
        return size;
    }
    public float getAttackAnimationScale(float p_32813_) {
        return (this.clientSideAttackTime + p_32813_) / (float)80;
    }
}

