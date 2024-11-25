package com.moonstone.moonstonemod.entity.other;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Particles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class flysword extends ThrowableItemProjectile {
    public int age = 0;
    public flysword(EntityType<? extends flysword> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.IRON_SWORD;
    }
    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        age++;
        if (age > 100) {
            this.discard();
        }
        if (age > 20) {
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(Particles.blue.get(), this.getX(), this.getEyeY(), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();

        if (age > 20) {
            if (entity instanceof LivingEntity livingEntity) {
                if (Handler.hascurio(livingEntity, com.moonstone.moonstonemod.init.Items.doomeye.get())) {
                    return;
                } else {
                    entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);

                }
                if (Handler.hascurio(livingEntity, com.moonstone.moonstonemod.init.Items.doomswoud.get())) {
                    return;
                } else {
                    entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);
                }
            }
        }
    }

}
