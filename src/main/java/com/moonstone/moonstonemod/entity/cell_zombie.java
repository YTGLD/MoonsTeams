package com.moonstone.moonstonemod.entity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class cell_zombie extends TamableAnimal{
    public cell_zombie(EntityType<? extends cell_zombie> c  , Level p_34272_) {
        super(c, p_34272_);
    }

    public int time = 0;
    @Override
    public void tick() {

        super.tick();
        if (!this.getTags().contains(AllEvent.muMMY)) {
            this.time+=2;
        }else {
            this.time++;
        }
        if (this.time > 1000){
            this.kill();
        }
        if (this.getTags().contains(AllEvent.DamageCell)){
            if (this.getOwner()!= null) {
                this.getAttributes().addTransientAttributeModifiers(modifierMultimap(this.getOwner()));
            }
        }
    }

    private Multimap<Attribute, AttributeModifier> modifierMultimap(LivingEntity livingEntity){
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (Handler.hascurio(livingEntity, Items.cell.get())&&Handler.hascurio(livingEntity, Items.adrenaline.get())) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier((this.uuid), MoonStoneMod.MODID + "DamageCell", livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE), AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier((this.uuid), MoonStoneMod.MODID + "DamageCell", livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED), AttributeModifier.Operation.ADDITION));
        }
        return modifierMultimap;
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        cell_zombie wolf = EntityTs.cell_zombie.get().create(p_146743_);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);
                wolf.setTame(true);
            }
        }
        return wolf;
    }


    @Override
    public void die(@NotNull DamageSource p_21809_) {

        if (this.getTags().contains(AllEvent.boom)){
            this.level().explode(null, this.getX(), this.getY(), this.getZ(), 5.5f, true, Level.ExplosionInteraction.MOB);

        }

    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return super.getOwner();
    }

    @Override
    public void setOwnerUUID(@Nullable UUID p_21817_) {
        super.setOwnerUUID(p_21817_);
    }
    public void aiStep() {
        super.aiStep();
    }
    public boolean wantsToAttack(LivingEntity p_30389_, LivingEntity p_30390_) {
        if (!(p_30389_ instanceof Creeper) && !(p_30389_ instanceof Ghast)) {
            if (p_30389_ instanceof cell_zombie) {
                cell_zombie wolf = (cell_zombie)p_30389_;
                return !wolf.isTame() || wolf.getOwner() != p_30390_;
            } else if (p_30389_ instanceof Player && p_30390_ instanceof Player && !((Player)p_30390_).canHarmPlayer((Player)p_30389_)) {
                return false;
            } else if (p_30389_ instanceof AbstractHorse && ((AbstractHorse)p_30389_).isTamed()) {
                return false;
            } else {
                return !(p_30389_ instanceof TamableAnimal) || !((TamableAnimal)p_30389_).isTame();
            }
        } else {
            return false;
        }
    }
    public boolean doHurtTarget(Entity p_30372_) {
        boolean flag = p_30372_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, p_30372_);
        }

        return flag;
    }

    public boolean hurt(DamageSource p_30386_, float p_30387_) {
        if (this.isInvulnerableTo(p_30386_)) {
            return false;
        } else {
            Entity entity = p_30386_.getEntity();
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
            }

            if (entity instanceof  LivingEntity livingEntity){
                if (!Handler.hascurio(livingEntity,Items.necora.get())) {
                    this.setTarget(livingEntity);
                }
            }
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                p_30387_ = (p_30387_ + 1.0F) / 2.0F;
            }

            return super.hurt(p_30386_, p_30387_);
        }
    }
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Villager.class, false));

    }
}
