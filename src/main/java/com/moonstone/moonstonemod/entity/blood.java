package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.Particles;
import com.moonstone.moonstonemod.init.moonstoneitem.BookItems;
import com.moonstone.moonstonemod.item.blood.meet_heart;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
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


    public blood(EntityType<? extends blood> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

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


            if (target == null || !target.isAlive()) {
                findNewTarget();
            }

            float s = this.tickCount / 500f;
            if (target != null) {
                if (this.tickCount > 25) {

                    Vec3 targetPos = target.position().add(0, 0, 0); // 将 Y 坐标增加 heightOffset

                    Vec3 currentPos = this.position();
                    Vec3 direction = targetPos.subtract(currentPos).normalize();

                    Vec3 currentDirection = this.getDeltaMovement().normalize();

                    double angle = Math.acos(currentDirection.dot(direction)) * (180.0 / Math.PI);

                    if (angle > 15) {
                        double angleLimit = Math.toRadians(15); // 将5度转为弧度

                        Vec3 limitedDirection = currentDirection.scale(Math.cos(angleLimit)) // 计算缩放因子
                                .add(direction.normalize().scale(Math.sin(angleLimit))); // 根据目标方向进行调整

                        this.setDeltaMovement(limitedDirection.x * 0.5f, limitedDirection.y * 0.5f, limitedDirection.z * 0.5f);
                    } else {
                        this.setDeltaMovement(direction.x *0.5f, direction.y * 0.5f, direction.z * 0.5f);
                    }
                }
            }
        } else {
            if (tickCount>5){
                this.discard();
            }
        }

        Vec3 vec3 = getDeltaMovement();

        float speedMax = 10000;

        float xx = (float) (vec3.x / 2.2f);
        float yy = (float) (vec3.y / 2.2f);
        float zz = (float) (vec3.z / 2.2f);
        if (xx > speedMax) {xx = speedMax;}
        if (yy > speedMax) {yy = speedMax;}
        if (zz > speedMax) {zz = speedMax;}

        this.setNoGravity(true);
        this.setYRot(0);
        this.setXRot(0);
    }

    @Override
    public void playerTouch(@NotNull Player entity) {
        if (this.tickCount > 20) {
            super.playerTouch(entity);
            entity.level().addFreshEntity(new ItemEntity(entity.level(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.blood.get())));
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

            meet_heart.addEffectMeet(entity);
            this.discard();
        }
    }
    private void findNewTarget() {
        if (this.getOwner() instanceof LivingEntity living){
            this.target = living;
        }
    }
}
