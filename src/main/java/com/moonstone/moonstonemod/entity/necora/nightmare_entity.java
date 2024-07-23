package com.moonstone.moonstonemod.entity.necora;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.MSound;
import com.moonstone.moonstonemod.init.Particles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class nightmare_entity extends cell_zombie {

    public nightmare_entity(EntityType<? extends nightmare_entity> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
        this.setNoAi(true);
    }
    public boolean isPushable() {
        return false;
    }
    private float clientSideAttackTime = 0;
    public int time = 0;

    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }
    @Override
    public void setItemInHand(InteractionHand p_21009_, ItemStack p_21010_) {

    }

    public float getAttackAnimationScale(float p_32813_) {
        return (clientSideAttackTime + p_32813_) / (float)80;
    }
    public void tick() {
        super.tick();
        this.time++;
        if (this.time < 10){
            if (this.level() instanceof ServerLevel serverLevel){
                serverLevel.sendParticles(ParticleTypes.SONIC_BOOM,this.getX(),this.getY()+1,this.getZ(),2,0.2,0.2,0.2,0);
            }
        }
        if (this.time > 360){
            this.level().explode(this,this.getX(),this.getY()+1,this.getZ(),1, Level.ExplosionInteraction.NONE);
            this.discard();
        }else {

            if (this.time < 280) {
                if (this.time % 130 == 0) {
                    this.level().playSound(this, new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()), MSound.black_hold.get(), SoundSource.MUSIC, 0.66f, 0.66f);
                }
            }
        }
        if (!this.level().isClientSide) {
            Vec3 position = this.position();
            int is = 8;

            List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));

            for (LivingEntity es : ess) {
                Vec3 motion = position.subtract(es.position().add(0, es.getBbHeight() / 2, 0));
                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                    motion = motion.normalize();
                }
                if (!Handler.hascurio(es, Items.nightmare_heart.get())) {
                    es.setDeltaMovement(motion.scale(0.1));
                }
            }

            Vec3 playerPos = this.position();
            int range = 12;
            List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (LivingEntity livingentity : entities) {

                if (!livingentity.is(this)) {

                    if (clientSideAttackTime < 80) {
                        ++clientSideAttackTime;
                    }
                    double d5 = this.getAttackAnimationScale(0.0F);
                    double d0 = livingentity.getX() - this.getX();
                    double d1 = livingentity.getY(0.5D) - this.getEyeY();
                    double d2 = livingentity.getZ() - this.getZ();
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    double d4 = this.getRandom().nextDouble();
                    while (d4 < d3) {
                        d4 += 1.8D - d5 + this.getRandom().nextDouble() * (1.7D - d5);

                        if (this.level() instanceof ServerLevel serverLevel) {
                            if (!Handler.hascurio(livingentity, Items.nightmare_heart.get())) {
                                if (!livingentity.is(this)) {
                                    if (this.tickCount % 20 == 0) {
                                        serverLevel.sendParticles(Particles.gold.get(), this.getX() + d0 * d4, this.getEyeY() + 0.75 + d1 * d4, this.getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                    }
                                }
                            }
                        }
                    }
                    if (!Handler.hascurio(livingentity, Items.nightmare_heart.get())) {
                        if (!livingentity.is(this)) {
                            if (this.tickCount % 20 == 0) {

                                livingentity.hurt(livingentity.damageSources().magic(), 3 + livingentity.getMaxHealth() / 50);
                                livingentity.invulnerableTime = 0;
                            }
                        }
                    }
                }

            }
        }
    }

    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    public boolean hurt(DamageSource p_27424_, float p_27425_) {
        return false;
    }



}
