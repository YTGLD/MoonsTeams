package com.ytgld.moonstone.enttiy;

import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class AttackBlood extends ThrowableItemProjectile {
    private LivingEntity target;
    private final List<Vec3> trailPositions = new ArrayList<>();

    public float damages = 4;
    public float addDamgae = 0;
    public boolean follow = true;
    public boolean slime = false;
    public boolean boom = false;
    public boolean effect = false;
    public boolean isPlayer = false;
    public float speeds = 0.5f;
    public float maxTime = 200;
    public AttackBlood(EntityType<? extends AttackBlood> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

    }
    @Override
    public boolean isInWater() {
        return false;
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    public float getXRot() {
        return 0;
    }

    @Override
    public float getYRot() {
        return 0;
    }
    public int live = 50;

    private boolean canSee = true;
    public boolean canSeeClient = true;

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }
    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(0.01f, 0.01f);
    }
  

    public void attack(){

        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 1;

        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (LivingEntity entity : entities) {
            if (this.getOwner() != null) {
                if (!entity.is(this.getOwner()) && this.getOwner() instanceof Player player) {
                    ResourceLocation entitys = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                    if (!entitys.getNamespace().equals(Moonstone.MODID)) {
                        if (entity.isAlive()) {
                            entity.invulnerableTime = 0;
                            if (boom) {
                                this.level().explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 3, false, Level.ExplosionInteraction.NONE);
                            }


                            if (effect) {
                                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
                                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                            }
                            if (slime) {
                                player.heal(damages + addDamgae);
                            }
                            entity.hurt(this.getOwner().damageSources().playerAttack(player), (float) (damages + addDamgae + player.getMaxHealth() / 10 + player.getAttributeValue(Attributes.ATTACK_DAMAGE) / 10));
                            canSee = false;
                        }
                        canSeeClient = false;
                    }
                }
            }
        }
    }
    @Override
    public void tick() {
        tickCount ++;
        this.noPhysics = true;
        this.move(
                MoverType.SELF,
                this.getDeltaMovement()
        );



        if (canSee) {
            if (boom && tickCount >= maxTime) {
                this.level().explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 3, false, Level.ExplosionInteraction.NONE);
                canSee = false;
                canSeeClient =false;
            }

            if (this.tickCount > maxTime) {
                canSee = false;
                canSeeClient =false;
            }
            if (target != null) {
                if (!target.isAlive()) {
                    findNewTarget();
                }
            }
        }

        float s = 0.075F;
        if (canSee) {
            if (target != null) {
                if (follow) {
                    Vec3 targetPos = target.position().add(0, 0.5, 0);
                    Vec3 currentPos = this.position();
                    Vec3 direction = targetPos.subtract(currentPos).normalize();
                    Vec3 currentDirection = this.getDeltaMovement().normalize();
                    double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);
                    if (angle > 45) {
                        double angleLimit = Math.toRadians(45);
                        Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit))
                                .add(direction.normalize().scale(Math.sin(angleLimit)));
                        this.setDeltaMovement(limitedDirection.x * (0.125f + s), limitedDirection.y * (0.125f + s), limitedDirection.z * (0.125f + s));
                    } else {
                        this.setDeltaMovement(direction.x * (0.125f + s), direction.y * (0.125f + s), direction.z * (0.125f + s));
                    }
                } else {
                    if (this.tickCount == 2) {
                        Vec3 targetPos = target.position().add(0, 0.5, 0);
                        Vec3 currentPos = this.position();
                        Vec3 direction = targetPos.subtract(currentPos).normalize();
                        this.setDeltaMovement(direction.x * (2 + s), direction.y * (2 + s), direction.z * (2 + s));
                    }
                }
            }
        }else {
            this.setDeltaMovement(0,0,0);
        }
        if (canSee) {
            trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        }
        if (!trailPositions.isEmpty()) {
            if (trailPositions.size() > 30||!canSee) {
                trailPositions.remove(0);
            }
        }
        if (!canSee) {
            live--;
        }
        if (live<= 0) {
            this.discard();
        }
        this.setNoGravity(true);
        this.setYRot(0);
        this.setXRot(0);

        if (canSee) {
            attack();
        }
        super.tick();
    }

    public Entity owner;
    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    private void findNewTarget() {

        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (this.getOwner() != null) {
                if (!name.getNamespace().equals(Moonstone.MODID) && !(entity.is(this.getOwner()))) {
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

    public float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public void setBoom(boolean boom) {
        this.boom = boom;
    }


    public void setDamage(float damage) {
        damages = damage;
    }
    public void setAddDamgae(float addDamgae) {
        this.addDamgae = addDamgae;
    }
    public void setEffect(boolean effect) {
        this.effect = effect;
    }

    public void setSpeed(float speed) {
        speeds = speed;
    }

    public float getSpeeds() {
        return speeds;
    }

    public float getDamages() {
        return damages;
    }

    public void setHeal(boolean slime) {
        this.slime = slime;
    }

    public void setCannotFollow(boolean t) {
        follow = t;
    }
}

