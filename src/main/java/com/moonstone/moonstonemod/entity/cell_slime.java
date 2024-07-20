package com.moonstone.moonstonemod.entity;

import com.google.common.annotations.VisibleForTesting;
import com.moonstone.moonstonemod.init.EntityTs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class cell_slime extends cell_zombie{
    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(cell_slime.class, EntityDataSerializers.INT);

    public cell_slime(EntityType<? extends cell_slime> c, Level p_34272_) {
        super(c, p_34272_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40).add(Attributes.MOVEMENT_SPEED, 0.15).add(Attributes.KNOCKBACK_RESISTANCE, 0.2).add(Attributes.ATTACK_DAMAGE, 8);
    }
    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 8);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    @VisibleForTesting
    public void setSize(int p_33594_, boolean p_33595_) {
        int i = Mth.clamp(p_33594_, 1, 127);
        this.entityData.set(ID_SIZE, i);
        this.reapplyPosition();
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)(i * i));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(0.2F + 0.1F * (float)i));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)i);
        if (p_33595_) {
            this.setHealth(this.getMaxHealth());
        }

        this.xpReward = i;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth()<=1&& !(this.getMaxHealth() < 2)){
            cell_slime cell_slime = new cell_slime(EntityTs.cell_slime.get(),this.level());
            if (this.getSize() > 0.25) {

                cell_slime.setOwnerUUID(this.getOwnerUUID());
                cell_slime.setSize(this.getSize() / 2, true);
                cell_slime.teleportTo(this.getX(), this.getY(), this.getZ());

                this.level().levelEvent(2001, new BlockPos((int)this.getX(), (int) (this.getY() + 1), (int) this.getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));
                this.level().levelEvent(2001, new BlockPos((int)this.getX(), (int) (this.getY()), (int) this.getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));
                this.level().levelEvent(2001, new BlockPos((int)this.getX(), (int) (this.getY() + 1), (int) this.getZ()), Block.getId(Blocks.SLIME_BLOCK.defaultBlockState()));

                this.level().addFreshEntity(cell_slime);
                this.discard();
            }
        }
    }

}
