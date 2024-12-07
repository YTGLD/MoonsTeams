package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.item.nanodoom.million;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class as_sword extends ThrowableItemProjectile {
    public as_sword(EntityType<? extends as_sword> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }
    private LivingEntity target;
    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    public void playerTouch(@NotNull Player entity) {
        if (Handler.hascurio(entity, com.moonstone.moonstonemod.init.Items.million.get())) {
            CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.getTag() != null) {

                            if (stack.getTag().getInt(million.sizeLvl) < 30) {
                                stack.getTag().putInt(million.sizeLvl,stack.getTag().getInt(million.sizeLvl)+1);
                            }
                            if (stack.getTag().getInt(million.attackLvl) < 100) {
                                stack.getTag().putInt(million.attackLvl,stack.getTag().getInt(million.attackLvl)+1);
                            }
                            stack.getTag().putInt(million.allAttackTime,200);

                        }
                    }
                }
            });
        }

        if (this.tickCount > 33) {
            entity.heal(entity.getMaxHealth() / 20);

            this.discard();
        }
    }


    @Override
    protected boolean canHitEntity(Entity target) {
        return false;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {

    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {

    }

    @Override
    protected void onHit(HitResult result) {

    }

    private int coll;

    @Override
    protected Item getDefaultItem() {
        return Items.IRON_SWORD;
    }

    @Override
    public void tick() {
        super.tick();
        coll--;
        Vec3 playerPos = this.position().add(0, 0.5, 0);
        float range = 1.5f;
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (LivingEntity entity : entities){
            if (this.getOwner()!=null) {
                if (!entity.is(this.getOwner())&&this.getOwner() instanceof Player player){
                    ResourceLocation entitys = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                    if (!entitys.getNamespace().equals(MoonStoneMod.MODID)) {
                        if (this.tickCount > 10&&coll<=0) {
                            if (player.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                                entity.invulnerableTime = 0;
                                entity.knockback(0.1f, Mth.nextFloat(RandomSource.create(),-0.1f,0.1f), Mth.nextFloat(RandomSource.create(),-0.1f,0.1f));
                                entity.hurt(this.getOwner().damageSources().dryOut(),
                                        (float) (0.3f + player.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 0.07f));
                                coll=20;
                            }
                        }
                    }
                }
            }
        }

        if (this.tickCount<200) {
            if (target == null || !target.isAlive()) {
                findNewTarget();
            }
        }else {
            if (this.getOwner()!=null) {
                if (this.getOwner() instanceof Player player) {
                    target =player;
                }
            }
        }


        this.addDeltaMovement(new Vec3(Math.sin(this.tickCount / 2.5f)/200f,Math.sin(this.tickCount/ 2.5f)/200f,Math.sin(this.tickCount/ 2.5f)/200f));
        if (target != null) {
            if (this.tickCount > 30) {

                Vec3 targetPos = target.position().add(0, 0, 0); // 将 Y 坐标增加 heightOffset

                Vec3 currentPos = this.position();
                Vec3 direction = targetPos.subtract(currentPos).normalize();

                Vec3 currentDirection = this.getDeltaMovement().normalize();

                double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);

                if (angle > 10) {
                    double angleLimit = Math.toRadians(10); // 将5度转为弧度

                    Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit)) // 计算缩放因子
                            .add(direction.normalize().scale(Math.sin(angleLimit))); // 根据目标方向进行调整

                    this.setDeltaMovement(limitedDirection.x * 0.5f, limitedDirection.y * 0.5f, limitedDirection.z * 0.5f);
                } else {
                    this.setDeltaMovement(direction.x * 0.5f, direction.y * 0.5f, direction.z * 0.5f);
                }
            }

        }

        if (this.tickCount > 400) {
            this.discard();
        }
        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 5) {
            trailPositions.remove(0);
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
}
