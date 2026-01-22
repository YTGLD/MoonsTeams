package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.DamageTps;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class attack_blood extends ThrowableItemProjectile {
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
    public attack_blood(EntityType<? extends attack_blood> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

    }
    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public void move(MoverType type, Vec3 pos) {

    }
    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        if (Handler.inBlackList(target)){
            return;
        }
        this.target = target;
    }

    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.blood.get();
    }

    @Override
    public @NotNull ItemStack getItem() {
        return Items.blood.get().getDefaultInstance();
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

    public boolean canSee = true;

    @Override
    public void tick() {
        super.tick();
        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 1;
        if (canSee) {
            List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (LivingEntity entity : entities) {
                if (this.getOwner() != null) {
                    if (!entity.is(this.getOwner()) && this.getOwner() instanceof Player player) {
                        ResourceLocation entitys = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                        if (!entitys.getNamespace().equals(MoonStoneMod.MODID)) {
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
                                if (isPlayer) {
                                    entity.hurt(this.getOwner().damageSources().playerAttack(player), (float) (damages + addDamgae + player.getMaxHealth() / 10 + player.getAttributeValue(Attributes.ATTACK_DAMAGE) / 10));
                                } else {
                                    entity.hurt(DamageTps.abyssDamage(entity), (float) (damages + addDamgae + player.getMaxHealth() / 10 + player.getAttributeValue(Attributes.ATTACK_DAMAGE) / 10));
                                }
                                canSee = false;
                            }
                        }
                    }
                }
            }
        }

        if (canSee) {
            if (boom && tickCount >= maxTime) {
                this.level().explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), 3, false, Level.ExplosionInteraction.NONE);
                canSee = false;
            }

            if (this.tickCount > maxTime) {
                canSee = false;
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

                    // 获取当前运动方向
                    Vec3 currentDirection = this.getDeltaMovement().normalize();

                    // 计算目标方向与当前方向之间的夹角
                    double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);

                    // 如果夹角超过10度，则限制方向
                    if (angle > 45) {
                        // 计算旋转后的新方向
                        double angleLimit = Math.toRadians(45); // 将10度转为弧度

                        // 根据正弦法则计算限制后的方向
                        Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit)) // 计算缩放因子
                                .add(direction.normalize().scale(Math.sin(angleLimit))); // 根据目标方向进行调整

                        this.setDeltaMovement(limitedDirection.x * (0.125f + s), limitedDirection.y * (0.125f + s), limitedDirection.z * (0.125f + s));
                    } else {
                        this.setDeltaMovement(direction.x * (0.125f + s), direction.y * (0.125f + s), direction.z * (0.125f + s));
                    }
                } else {
                    if (this.tickCount == 1) {
                        Vec3 targetPos = target.position().add(0, 0.5, 0);
                        Vec3 currentPos = this.position();
                        Vec3 direction = targetPos.subtract(currentPos).normalize();
                        this.setDeltaMovement(direction.x * (speeds + s), direction.y * (speeds + s), direction.z * (speeds + s));
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
    }
    private void findNewTarget() {

        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            Set<ResourceLocation> blacklist = new HashSet<>();
            for (String aaa : Config.SERVER.attackTarget.get()) {
                String[] parts = aaa.split(":");
                if (parts.length > 0) {
                    blacklist.add( new ResourceLocation(parts[0],parts[1]));
                }
            }
            if (blacklist.contains(name)){
                return;
            }
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

