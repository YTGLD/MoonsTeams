package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.init.Particles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class sword extends cell_zombie {
    public int age = 0;
    public sword(@NotNull EntityType<sword> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }
    public boolean isPushable() {
        return false;
    }

    protected void doPush(Entity p_27415_) {
    }

    protected void pushEntities() {
    }

    @Override
    public boolean hurt(DamageSource p_30386_, float p_30387_) {
        return false;
    }

    public void tick() {
        super.tick();
        this.setNoGravity(true);
        this.setNoAi(true);
        this.age ++;
        if (age > 2){
            Vec3 position = this.position();
            float is = 2;
            {
                List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
                for (LivingEntity es : ess) {
                    if (this.getOwner() != null) {
                        if (this.getOwner() instanceof Player living) {
                            if (!es.is(living)) {
                                if (living.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                                    if (this.age % 10 == 0) {
                                        es.hurt(es.damageSources().magic(), (float) (living.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 0.1f));
                                        es.invulnerableTime = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }else {
            if (this.level() instanceof ServerLevel serverLevel){
                serverLevel.sendParticles(Particles.blue.get(), this.getX(), this.getEyeY(), this.getZ(), 2, 0.0D, 0.0D, 0.0D, 0);
            }
        }
        if (age > 30) {
            this.discard();
        }
    }

}


