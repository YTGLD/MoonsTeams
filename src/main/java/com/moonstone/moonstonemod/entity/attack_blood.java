package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class attack_blood extends ThrowableItemProjectile {
    private LivingEntity target;
    private final List<Vec3> trailPositions = new ArrayList<>();

    public attack_blood(EntityType<? extends attack_blood> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

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

    @Override
    public void tick() {
        super.tick();
        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 1;
        List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (Mob entity : entities){
            if (this.getOwner()!=null) {
                if (!entity.is(this.getOwner())&&this.getOwner() instanceof Player player){
                    ResourceLocation entitys = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                    if (!entitys.getNamespace().equals(MoonStoneMod.MODID)) {
                        entity.invulnerableTime = 0;
                        entity.hurt(this.getOwner().damageSources().dryOut(), 4 + player.getMaxHealth() / 10);
                        this.discard();
                    }
                }
            }
        }
        if (this.tickCount > 200) {
            this.discard();
        }
        if (target != null) {
            if (!target.isAlive()) {
                findNewTarget();

            }
        }

        float s = this.tickCount / 200f;
        if (target != null) {
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

                this.setDeltaMovement(limitedDirection.x * (0.125f+s), limitedDirection.y *(0.125f+s), limitedDirection.z * (0.125f+s));
            } else {
                this.setDeltaMovement(direction.x * (0.125f+s), direction.y * (0.125f+s), direction.z * (0.125f+s));
            }
        }

        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 20) {
            trailPositions.remove(0);
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

}

