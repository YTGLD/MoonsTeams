package com.moonstone.moonstonemod.entity.other;

import com.google.gson.JsonArray;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Particles;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
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

        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size()>50){
            trailPositions.remove(0);
        }
        if (trailPositions.size() > 6) {
            Vec3 lastPosition = trailPositions.get(trailPositions.size() - 2);
            Vec3 currentPosition = trailPositions.get(trailPositions.size() - 1);
            if (lastPosition.equals(currentPosition)) {
                trailPositions.remove(0);
                trailPositions.remove(1);
                trailPositions.remove(2);
                trailPositions.remove(3);
            }
        }

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
        if (age > 50) {
            if (trailPositions.size()>1){
                trailPositions.remove(0);
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
    private final List<Vec3> trailPositions = new ArrayList<>();

    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
}
