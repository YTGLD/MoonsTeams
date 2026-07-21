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
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CellZombie extends ExtendEntityLiving {
    public CellZombie(EntityType<? extends CellZombie> c  , Level p_34272_) {
        super(c, p_34272_);
    }

    public int time = 0;
    @Override
    public void tick() {
        super.tick();
        {
            Vec3 playerPos = this.position().add(0, 0.75, 0);
            int range = 10;
            List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Mob mob : entities) {
                if (this.getTarget() == null) {
                    Identifier entity = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
                    if (!entity.getNamespace().equals(Moonstone.MODID)) {
                        this.setTarget(mob);
                    }
                }
            }
        }
        if (!this.entityTags().contains(AllEvent.muMMY)) {
            this.time+=2;
        }else {
            this.time++;
        }
        if (this.time > 1000){
            if (level() instanceof ServerLevel serverLevel) {
                this.kill(serverLevel);
            }
        }
        if (this.entityTags().contains(AllEvent.DamageCell)){
            if (this.getOwner()!= null) {
                this.getAttributes().addTransientAttributeModifiers(modifierMultimap(this.getOwner()));
            }
        }
        if (this.entityTags().contains(AllEvent.calcification)){
            if (this.getOwner()!= null) {
                this.getAttributes().addTransientAttributeModifiers(calcificationMultimap(this.getOwner()));
            }
        }
    }
    private Multimap<Holder<Attribute>, AttributeModifier> calcificationMultimap(LivingEntity livingEntity){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (Handler.hascurio(livingEntity, Items.cell.get())&& Handler.hascurio(livingEntity, Items.cell_calcification.get())) {
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.stringUUID),  livingEntity.getAttributeValue(Attributes.ARMOR) / 2, AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.stringUUID),  livingEntity.getAttributeValue(Attributes.MAX_HEALTH) / 2, AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    private Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap(LivingEntity livingEntity){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (Handler.hascurio(livingEntity, Items.cell.get())&&Handler.hascurio(livingEntity, Items.adrenaline.get())) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.stringUUID),  livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE), AttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(Moonstone.MODID,this.stringUUID),  livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED), AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }


    @Override
    public void die(@NotNull DamageSource p_21809_) {

        if (this.entityTags().contains(AllEvent.boom)){
            this.level().explode(null, this.getX(), this.getY(), this.getZ(), 5.5f, false, Level.ExplosionInteraction.NONE);

        }
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return super.getOwner();
    }

    public void aiStep() {
        super.aiStep();
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    protected @org.jspecify.annotations.Nullable SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    @Override
    protected @org.jspecify.annotations.Nullable SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ZOMBIE_HURT;
    }

    public boolean wantsToAttack(LivingEntity p_30389_, LivingEntity p_30390_) {
        if (!(p_30389_ instanceof CellGiant) && !(p_30389_ instanceof CellZombie)) {
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

    public boolean doHurtTarget(ServerLevel level, Entity target) {

        if (this.entityTags().contains(AllEvent.cb_blood)){
            this.heal(this.getMaxHealth()/10);
            if (this.time>0) {
                this.time -= 100;
            }
            this.level().playSound(null,new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()), SoundEvents.PANDA_EAT, SoundSource.MUSIC,2,2);
        }
        return super.doHurtTarget(level,target);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (this.isInvulnerableTo(level,source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if (!this.level().isClientSide()) {
                this.setOrderedToSit(false);
            }

            if (entity instanceof  LivingEntity livingEntity){
                if (!Handler.hascurio(livingEntity,Items.necora.get())) {
                    this.setTarget(livingEntity);
                }
            }
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                damage = (damage + 1.0F) / 2.0F;
            }

            return super.hurtServer(level,source,damage);
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
    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }
    @Override
    public void setItemInHand(InteractionHand p_21009_, ItemStack p_21010_) {

    }
}
