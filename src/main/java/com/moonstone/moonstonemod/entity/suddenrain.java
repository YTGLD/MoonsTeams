package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class suddenrain extends ThrowableItemProjectile {
    public int age = 0;
    public suddenrain(@NotNull EntityType<?extends suddenrain> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }


    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.ambush.get();
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return super.getOwner();
    }
    private LivingEntity target;

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        age++;

        if (age > 220) {
            this.discard();
        }
        if (target == null || !target.isAlive()) {
            findNewTarget();
        }

        if (target != null) {
            Vec3 targetPos = target.position().add(0, 0.5, 0);
            Vec3 currentPos = this.position();
            Vec3 direction = targetPos.subtract(currentPos).normalize();
            this.setDeltaMovement(direction.x *0.375f, direction.y *0.375f, direction.z *0.375f);
        }
       
    }

    private void findNewTarget() {
        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (this.getOwner() != null) {
                if (!name.getNamespace().equals(MoonStoneMod.MODID) && !(entity.is(this.getOwner()))) {
                    double distance = this.distanceToSqr(entity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }

        this.target = closestEntity;
    }
    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        if (entity instanceof LivingEntity livingEntity){
            if (this.getOwner() instanceof LivingEntity living) {
                if (!livingEntity.is(living)){
                    livingEntity.invulnerableTime = 0;
                    livingEntity.hurt(this.damageSources().magic(), 2 + living.getMaxHealth()/20);
                    this.discard();
                }
            }
        }
        this.discard();
    }



}

