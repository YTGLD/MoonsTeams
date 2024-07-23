package com.moonstone.moonstonemod.entity.necora;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class red_entity extends cell_zombie {

    public red_entity(EntityType<? extends red_entity> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
        this.setNoAi(true);
    }


    public int time = 0;
    public float size = 6;
    public float sw = 1;
    public void tick() {
        super.tick();
        this.time++;
        if (this.time > 120) {
            this.discard();
        }
        sw-= 0.01f;
        if (time == 1) {
            this.level().playSound(null,new BlockPos((int)this.getX(), (int) this.getY(), (int) this.getZ()), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.MASTER,sw,sw);
        }
        if (time % 20 == 0) {
            this.level().playSound(null,new BlockPos((int)this.getX(), (int) this.getY(), (int) this.getZ()), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.MASTER,sw,sw);
        }
        size+=0.05f;
        Vec3 position = this.position();
        float is = size;
        List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
        for (LivingEntity es : ess){
            if (this.getOwner()!= null) {
                if (!es.is(this.getOwner())) {
                    if (this.getOwner().getAttribute(Attributes.ATTACK_DAMAGE)!= null) {
                        es.hurt(es.damageSources().dryOut(), (float) (this.getOwner().getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 2));

                        double d0 = this.getX() - es.getX();
                        double d1;
                        for (d1 = this.getZ() - es.getZ(); d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }
                        es.knockback(0.5f, d0, d1);
                    }
                }
            }
        }
    }


    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }

    @Override
    public void setItemInHand(InteractionHand p_21009_, ItemStack p_21010_) {

    }
}

