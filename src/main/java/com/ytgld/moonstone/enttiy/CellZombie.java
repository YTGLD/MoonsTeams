package com.ytgld.moonstone.enttiy;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.event.AllEvent;
import com.ytgld.moonstone.item.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CellZombie extends ExtendZombieEntity {
    public CellZombie(EntityType<? extends CellZombie> c  , Level p_34272_) {
        super(c, p_34272_);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTarget() != null) {
            ResourceLocation entity = BuiltInRegistries.ENTITY_TYPE.getKey(this.getTarget().getType());
            if (entity.getNamespace().equals(Moonstone.MODID)) {
                this.setTarget(null);
            }
        }

        setAttackT();

        if (this.getTarget()!=null) {
            if (!this.getTarget().isAlive()) {
                this.setTarget(null);
            }else {
                if (this.getTarget() instanceof Player entity) {
                    if (this.getOwner()!=null) {
                        if (this.getOwner().is(entity)) {
                            this.setTarget(null);
                        }
                    }
                }
                if (this.getTarget() instanceof OwnableEntity entity) {
                    if (this.getOwner()!=null) {
                        if (entity.getOwner() != null && entity.getOwner().is(this.getOwner())) {
                            this.setTarget(null);
                        }
                    }
                }
            }
        }

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
        if (this.getTags().contains(AllEvent.calcification)){
            if (this.getOwner()!= null) {
                this.getAttributes().addTransientAttributeModifiers(calcificationMultimap(this.getOwner()));

                if (this.tickCount < 5){
                    this.heal(100);
                }
            }
        }
    }
    private void setAttackT(){

        if (this.getOwner()!= null) {
            if (this.getTarget() instanceof OwnableEntity ownableEntity){
                if (ownableEntity.getOwner()!=null&&ownableEntity.getOwner().is(this.getOwner())){
                    return;
                }
            }



            if (this.getOwner().getLastHurtByMob()!= null) {
                if (!this.getOwner().getLastHurtByMob().is(this)&&!BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastHurtByMob().getType()).getNamespace().equals(Moonstone.MODID)) {
                    this.setTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker()!= null) {
                if (!this.getOwner().getLastAttacker().is(this)&&!BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastAttacker().getType()).getNamespace().equals(Moonstone.MODID)) {
                    this.setTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob()!= null) {
                if (!this.getOwner().getLastHurtMob().is(this)&&!BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastHurtMob().getType()).getNamespace().equals(Moonstone.MODID)) {
                    this.setTarget(this.getOwner().getLastHurtMob());
                }

            }
        }
    }
    private Multimap<Holder<Attribute>, AttributeModifier> calcificationMultimap(LivingEntity livingEntity){
       Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (Handler.hascurio(livingEntity, Items.cell.get())&& Handler.hascurio(livingEntity, Items.cell_calcification.get())) {
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+"cell_armor"), livingEntity.getAttributeValue(Attributes.ARMOR) / 2, AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+"cell_health"), livingEntity.getAttributeValue(Attributes.MAX_HEALTH) / 2, AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    private Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap(LivingEntity livingEntity){
       Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (Handler.hascurio(livingEntity, Items.cell.get())&&Handler.hascurio(livingEntity, Items.adrenaline.get())) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+"cell_damage"), livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE), AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+"cell_speed"), livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED), AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        CellZombie wolf = EntityTs.cell_zombie.get().create(p_146743_);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);
                wolf.setTame(true,true);
            }
        }
        return wolf;
    }


    @Override
    public void die(@NotNull DamageSource p_21809_) {
        super.die(p_21809_);

        if (this.getTags().contains(AllEvent.boom)){
            this.level().explode(null, this.getX(), this.getY(), this.getZ(), 5.5f, false, Level.ExplosionInteraction.NONE);

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

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    public boolean wantsToAttack(LivingEntity p_30389_, LivingEntity p_30390_) {
        if (!(p_30389_ instanceof Creeper) && !(p_30389_ instanceof Ghast)) {
            if (p_30389_ instanceof CellZombie) {
                CellZombie wolf = (CellZombie)p_30389_;
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
        if (this.getTags().contains(AllEvent.cb_blood)){
            this.heal(this.getMaxHealth()/10);
            if (this.time>0) {
                this.time -= 100;
            }
            this.level().playSound(null,new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()), SoundEvents.PANDA_EAT, SoundSource.MUSIC,2,2);
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
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Villager.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Zombie.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Spider.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Skeleton.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Creeper.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, EnderMan.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));
    }
}
