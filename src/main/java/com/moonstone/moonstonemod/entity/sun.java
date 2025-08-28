package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.other.blood_zombie_boom;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class sun extends ThrowableItemProjectile {
    private LivingEntity target;

    public sun(EntityType<? extends sun> entityType, Level level) {
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

    private int time = 200;

    @Override
    public void tick() {
        super.tick();
        time--;
        if (target == null || !target.isAlive()) {
            findNewTarget();
        }
        if (time<2){
            blood_zombie_boom attack_blood=  new blood_zombie_boom(EntityTs.blood_zombie_boom.get(),this.level());
            attack_blood.setPos(this.position());
            this.level().addFreshEntity(attack_blood);
            this.playSound(SoundEvents.GENERIC_EXPLODE, 1.5F, 1.5F);


            this.discard();

        }
       
        if (this.tickCount < 20) {
            this.setPos(new Vec3(this.getX(), this.getY() + 0.15f, this.getZ()));
        }else{
            this.setPos(new Vec3(this.getX(), this.getY() + 0.015f, this.getZ()));


            if (this.tickCount%(5)==0&&this.target !=null) {
                attack_blood attack_blood = new attack_blood(EntityTs.attack_blood.get(), this.level());
                attack_blood.setTarget(this.target);
                attack_blood.setPos(this.position());
                attack_blood.setOwner(this.getOwner());
                this.level().addFreshEntity(attack_blood);

                playRemoveOneSound(this);
            }
        }


        this.setNoGravity(true);
        this.setYRot(0);
        this.setXRot(0);


    }
    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), 0.55f, 0.55f);
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

