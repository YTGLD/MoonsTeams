package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class blood extends ThrowableItemProjectile {
    private LivingEntity target;
    private final List<Vec3> trailPositions = new ArrayList<>();

    public blood(EntityType<? extends blood> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

    }

    @Override
    public boolean canCollideWith(Entity p_20303_) {
        return super.canCollideWith(p_20303_);
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

        if (!this.getTags().contains("Blood")) {
            if (this.tickCount % 100 == 0) {
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ELYTRA_FLYING, SoundSource.NEUTRAL, 0.31F, 0.31F);
            }
            if (target == null || !target.isAlive()) {
                findNewTarget();
            }

            float s = this.tickCount / 200f;
            if (target != null) {
                if (this.tickCount > 25) {
                    Vec3 targetPos = target.position().add(0, 0.5, 0);
                    Vec3 currentPos = this.position();
                    Vec3 direction = targetPos.subtract(currentPos).normalize();
                    this.setDeltaMovement(direction.x * (0.02f + s), direction.y * (0.02f + s), direction.z * (0.02f + s));
                }
            }
        } else {
            if (tickCount>6){
                this.discard();
            }
        }

        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 50) {
            trailPositions.remove(0);
        }

        this.setNoGravity(true);
        this.setYRot(0);
        this.setXRot(0);
    }

    @Override
    public void playerTouch(@NotNull Player entity) {
        if (this.tickCount > 20) {
            super.playerTouch(entity);
            entity.addItem(new ItemStack(Items.blood.get()));
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.RESPAWN_ANCHOR_DEPLETE.get(), SoundSource.NEUTRAL, 1.45f, 1.45f);




            this.discard();
        }
    }
    private void findNewTarget() {
        if (this.getOwner() instanceof LivingEntity living){
            this.target = living;
        }
    }
}
