package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        age++;

        if (age > 220) {
            this.discard();
        }

    }


    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        if (age > 30) {
            Entity entity = hitResult.getEntity();
            if (entity instanceof LivingEntity livingEntity){
                if (Handler.hascurio(livingEntity, com.moonstone.moonstonemod.init.Items.doomeye.get())){

                }else {
                    livingEntity.invulnerableTime = 0;
                    entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);
                    this.discard();

                }
                if (Handler.hascurio(livingEntity, com.moonstone.moonstonemod.init.Items.doomswoud.get())){

                }else {
                    livingEntity.invulnerableTime = 0;
                    entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);
                    this.discard();

                }
            }

        }
        this.discard();
    }



}

