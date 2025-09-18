package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.BookItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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

            float s = this.tickCount / 500f;
            if (target != null) {
                if (this.tickCount > 25) {
                    Vec3 targetPos = target.position().add(0, 0.5, 0);
                    Vec3 currentPos = this.position();
                    Vec3 direction = targetPos.subtract(currentPos).normalize();
                    this.setDeltaMovement(direction.x * (0.005f + s), direction.y * (0.005f + s), direction.z * (0.005f + s));
                }
            }
        } else {
            if (tickCount>5){
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

            if (Handler.hascurio(entity, Items.deceased_contract.get())){
                entity.heal(entity.getMaxHealth() * 0.15f);
            }

            if (this.getOwner()!=null && this.getOwner() instanceof Player player){
                if (Handler.hascurio(player, BookItems.bloodstain.get())){
                    player.level().explode(player,player.getX(),player.getY(),player.getZ(),3.5f,false, Level.ExplosionInteraction.NONE);
                }
                if (Handler.hascurio(player, BookItems.spore_outbreak.get())){
                    player.heal(player.getMaxHealth()/5);
                }
                if (Handler.hascurio(player, BookItems.weak.get())){
                    player.hurt(player.damageSources().dryOut(),player.getMaxHealth()/10);
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,200,2));
                }
            }

            this.discard();
        }
    }
    private void findNewTarget() {
        if (this.getOwner() instanceof LivingEntity living){
            this.target = living;
        }
    }
}
