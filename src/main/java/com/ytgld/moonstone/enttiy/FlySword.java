package com.ytgld.moonstone.enttiy;

import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.other.ITrail;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.List;

public class FlySword extends ThrowableItemProjectile implements ITrail {
    public FlySword(EntityType<? extends FlySword> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }
    private LivingEntity target;
    private final List<Vec3> trailPositions = new ArrayList<>();
    public int live = 50;
    public boolean canSee = true;
    public int attackSize = 0;
    public final int maxAttack = 5;
    public int cooldown = 0;
    public void setTarget(LivingEntity target) {
        this.target = target;
    }
    @Override
    public void tick() {
        super.tick();
        if (!(getOwner() instanceof Player player)) {
            return;
        }
        this.noPhysics = true;
        this.setNoGravity(true);
        this.setPos(
                this.getX() + this.getDeltaMovement().x,
                this.getY() + this.getDeltaMovement().y,
                this.getZ() + this.getDeltaMovement().z
        );
        if (canSee) {
            Entity target = this.target;
            if (target instanceof LivingEntity livingEntity) {
                if (tickCount > 30) {
                    float size = Math.min((tickCount - 30f) / 100f, 0.63f);

                    Vec3 targetPos = livingEntity.getEyePosition();
                    Vec3 direction = targetPos.subtract(position()).normalize();

                    Vec3 targetVelocity = direction.scale(size)
                            .add(
                                    Math.cos(tickCount / 7.5f) / 20f,
                                    Math.sin(tickCount / 7.5f) / 20f,
                                    0
                            );

                    Vec3 smoothVelocity = getDeltaMovement()
                            .scale(1.1f)
                            .lerp(targetVelocity, 0.08);

                    setDeltaMovement(smoothVelocity);
                }
            }
        }else {
            setDeltaMovement(0,0,0);
            live--;
            if (live <= 0) {
                this.discard();
            }
        }
        if (attackSize >= maxAttack) {
            canSee = false;
        }
        if (cooldown > 0) {
            cooldown --;
        }
        if (this.tickCount > 200) {
            canSee = false;
        }
        if(canSee){
            trailPositions.add(position());
        }
        if (!trailPositions.isEmpty()) {
            if (trailPositions.size() > 15 || !canSee) {
                trailPositions.removeFirst();
            }
        }
        if (this.target == null) {
            this.target = player.getLastHurtByMob();
        }else if (!this.target.isAlive()){
            target = null;
        }
        attack();
    }
    public void attack(){
        if (attackSize >= maxAttack) {
            canSee = false;
            return;
        }
        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 2;
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (LivingEntity entity : entities) {
            if (this.getOwner() != null) {
                if (!entity.is(this.getOwner()) && this.getOwner() instanceof Player player) {
                    Identifier entitys = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                    if (!entitys.getNamespace().equals(Moonstone.MODID)) {
                        if (entity.isAlive()) {
                            entity.setInvulnerableTime(0);
                            entity.damageCooldownTime = 0;
                            if (cooldown <= 0) {
                                entity.hurt(entity.damageSources().mobProjectile(this,player),2);
                                attackSize++;
                                cooldown = 10;
                            }
                        }
                    }
                }
            }
        }
    }
    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.ITEM_FRAME.asItem();
    }

    public ProjectileDeflection deflection(Projectile projectile) {
        return ProjectileDeflection.NONE;
    }
    @Override
    public List<Vec3> trailPositions() {
        return trailPositions;
    }
}
