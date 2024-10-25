package com.moonstone.moonstonemod.entity.zombie;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.EntityTs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class blood_zombie_fly extends ThrowableItemProjectile {
    public int age = 0;
    private LivingEntity target;
    public blood_zombie_fly(EntityType<? extends blood_zombie_fly> p_37248_, Level p_37249_) {
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
        this.setInvisible(true);
        age++;
        if (age > 100) {
            this.discard();
        }
        if (target == null || !target.isAlive()) {
            findNewTarget();
        }

        if (target != null) {
            if (this.age > 20) {
                Vec3 targetPos = target.position().add(0,0.5,0);
                Vec3 currentPos = this.position();
                Vec3 direction = targetPos.subtract(currentPos).normalize();
                this.setDeltaMovement(direction.x/3,direction.y/3,direction.z/3);
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
        if (!name.getNamespace().equals(MoonStoneMod.MODID)&& !(entity instanceof Player)) {
            if (this.age > 20) {
                entity.invulnerableTime = 0;
                entity.hurt(this.damageSources().thrown(this, this.getOwner()), 2.5f);
                blood_zombie_boom blood_zombie_boom = new blood_zombie_boom(EntityTs.blood_zombie_boom.get(), this.level());
                blood_zombie_boom.setPos(this.position());

                this.level().addFreshEntity(blood_zombie_boom);
                this.discard();
            }
        }
    }

    private void findNewTarget() {
        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (!name.getNamespace().equals(MoonStoneMod.MODID)&& !(entity instanceof Player)) {
                double distance = this.distanceToSqr(entity);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestEntity = entity;
                }
            }
        }

        this.target = closestEntity;
    }

}
