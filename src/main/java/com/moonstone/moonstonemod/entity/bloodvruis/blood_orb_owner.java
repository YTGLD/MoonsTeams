package com.moonstone.moonstonemod.entity.bloodvruis;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class blood_orb_owner extends TamableAnimal {
    public blood_orb_owner(EntityType<? extends blood_orb_owner> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
        this.setNoGravity(true);
    }
    @Override
    public void die(@NotNull DamageSource p_21809_) {

    }

    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        this.setXRot(0);
        this.setYRot(0);

        LivingEntity owner = getOwner(); // 获取主人
        LivingEntity target = getTarget(); // 获取目标

        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 66) {
            trailPositions.remove(0);
        }

        Vec3 currentPos = this.position();

        if ( target != null) {
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING,10, 2,false,false));
            Vec3 targetPos = target.position().add(0, 0.5, 0);
            Vec3 direction = targetPos.subtract(currentPos).normalize();
            this.setDeltaMovement(direction.x * (0.075f + 0.5), direction.y * (0.075f + 0.5), direction.z * (0.075f + 0.5));
        }
        if (owner != null){
            float yRot =owner.getYRot();
            float xRot =owner.getXRot();

            this.setXRot(xRot);
            this.setYRot(yRot);


            double desiredDistance = 2; // 设置想要保持的距离
            Vec3 targetPos = owner.position().add(0, 3, 0); // 获取玩家位置并抬高

            Vec3 forward = owner.getLookAngle(); // 获取玩家的朝向向量
            Vec3 direction = forward.scale(-1).normalize(); // 计算背后的方向（逆向）

            Vec3 newTargetPos = targetPos.add(direction.scale(desiredDistance)); // 计算新的目标位置

            this.setDeltaMovement(newTargetPos.subtract(currentPos).normalize().scale(0.15f)); // 设置对象的运动速度
        }




        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 20;
        List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (Mob mob : entities) {
            if (this.getTarget() == null) {
                ResourceLocation entity = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
                if (!entity.getNamespace().equals(MoonStoneMod.MODID)) {
                    this.setTarget(mob);
                }
            }
        }
        if (this.getTarget() != null) {
            if (!this.getTarget().isAlive()) {
                this.setTarget(null);
            }
        }
        if (this.getOwner()!= null) {
            if (this.getOwner().getLastHurtByMob()!= null) {
                if (!this.getOwner().getLastHurtByMob().is(this)) {
                    this.setTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker()!= null) {
                if (!this.getOwner().getLastAttacker().is(this)) {
                    this.setTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob()!= null) {
                if (!this.getOwner().getLastHurtMob().is(this)) {
                    this.setTarget(this.getOwner().getLastHurtMob());
                }
            }
        }
        if (this.getTarget()!=null&&this.getOwner() instanceof Player player){
            if (this.tickCount % 100 == 0) {
                blood_orb_attack attack_blood = new blood_orb_attack(EntityTs.blood_orb_attack.get(), this.level());


                attack_blood.setPos(this.position());
                attack_blood.setOwner(this.getOwner());


                this.level().addFreshEntity(attack_blood);

                playRemoveOneSound(this);
            }
        }

    }

    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, 1.8f, 1.8F);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    protected void doPush(Entity p_27415_) {
    }
    protected void pushEntities() {
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F,false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        blood_orb_owner wolf = EntityTs.blood_orb_owner.get().create(serverLevel);
        if (wolf != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                wolf.setOwnerUUID(uuid);
                wolf.setTame(true);
            }
        }
        return wolf;
    }
}

