package com.ytgld.moonstone.enttiy;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.logging.LogUtils;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.event.AllEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.warden.AngerLevel;
import net.minecraft.world.entity.monster.warden.AngerManagement;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

public class CellGiant extends ExtendEntityLiving implements OwnableEntity {
    public static final Logger LOGGER = LogUtils.getLogger();
    public int tendrilAnimation;
    public int tendrilAnimationO;
    public int heartAnimation;
    public int heartAnimationO;
    public AnimationState roarAnimationState = new AnimationState();
    public AnimationState sniffAnimationState = new AnimationState();
    public AnimationState emergeAnimationState = new AnimationState();
    public AnimationState diggingAnimationState = new AnimationState();
    public AnimationState attackAnimationState = new AnimationState();
    public AnimationState sonicBoomAnimationState = new AnimationState();

    public final VibrationSystem.User vibrationUser;
    public VibrationSystem.Data vibrationData;
    public CellGiant(EntityType<? extends CellGiant> c  , Level p_34272_) {
        super(c, p_34272_);
        this.vibrationUser = new VibrationUser();
        this.vibrationData = new VibrationSystem.Data();
        this.xpReward = 5;
        this.getNavigation().setCanFloat(true);
    }

    public int time = 0;

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 45).add(Attributes.MOVEMENT_SPEED, 0.2).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.ATTACK_KNOCKBACK, 1.5D).add(Attributes.ATTACK_DAMAGE, 10);
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }
    @Override
    public void die(@NotNull DamageSource p_21809_) {
        if (this.entityTags().contains(AllEvent.Parasitic_cell_Giant)){
             for (int i = 0; i < 6; i++) {
                 CellZombie CellZombie = new CellZombie(EntityTs.cell_zombie.get(),this.level());
                 CellZombie.setPos(this.getX(),this.getY(),this.getY());
                 if (this.getOwner()!=null) {
                    CellZombie.setOwner(this.getOwner());
                 }
                this.level().addFreshEntity(CellZombie);

            }
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
    public float getHeartAnimation(float p_219470_) {
        return Mth.lerp(p_219470_, (float)this.heartAnimationO, (float)this.heartAnimation) / 10.0F;
    }

    public float getTendrilAnimation(float p_219468_) {
        return Mth.lerp(p_219468_, (float)this.tendrilAnimationO, (float)this.tendrilAnimation) / 10.0F;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity serverEntity) {
        return new ClientboundAddEntityPacket(this, serverEntity, this.hasPose(Pose.EMERGING) ? 1 : 0);
    }

    public void recreateFromPacket(ClientboundAddEntityPacket p_219420_) {
        super.recreateFromPacket(p_219420_);
        if (p_219420_.getData() == 1) {
            this.setPose(Pose.EMERGING);
        }

    }

    public boolean checkSpawnObstruction(LevelReader p_219398_) {
        return super.checkSpawnObstruction(p_219398_) && p_219398_.noCollision(this, this.getType().getDimensions().makeBoundingBox(this.position()));
    }

    public float getWalkTargetValue(BlockPos p_219410_, LevelReader p_219411_) {
        return 0.0F;
    }


    boolean isDiggingOrEmerging() {
        return this.hasPose(Pose.DIGGING) || this.hasPose(Pose.EMERGING);
    }

    protected boolean canRide(Entity p_219462_) {
        return false;
    }

    public boolean canDisableShield() {
        return true;
    }

    protected float nextStep() {
        return this.moveDist + 1.5f;
    }

    public boolean dampensVibrations() {
        return true;
    }

    protected float getSoundVolume() {
        return 4.0F;
    }

    @javax.annotation.Nullable
    protected SoundEvent getAmbientSound() {
        return !this.hasPose(Pose.ROARING) && !this.isDiggingOrEmerging() ? this.getAngerLevel().getAmbientSound() : null;
    }

    protected SoundEvent getHurtSound(DamageSource p_219440_) {
        return SoundEvents.ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected void playStepSound(BlockPos p_219431_, BlockState p_219432_) {
        this.playSound(SoundEvents.ZOGLIN_STEP, 1.0f, 1.0F);
    }

    public boolean doHurtTarget(ServerLevel level, Entity target) {
        this.level().broadcastEntityEvent(this, (byte)4);
        this.playSound(SoundEvents.PANDA_EAT, 10.0F, this.getVoicePitch());
        SonicBoom.setCooldown(this, 40);
        return super.doHurtTarget(level,target);
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);

    }

    public int getClientAngerLevel() {
        return 1;
    }
    public void sou() {
    }
    public Multimap<Holder<Attribute>, AttributeModifier> AttributeModifier(CellGiant cellGiant, LivingEntity living){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (cellGiant.entityTags().contains(AllEvent.Bone_Giant)) {
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(Identifier.parse(this.stringUUID) ,living.getAttributeValue(Attributes.ARMOR)* 0.7, AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    public void tick() {
        if (!this.entityTags().contains(AllEvent.Disgusting__cell_Giant)) {
            time += 2;
        }else {
            time++;
        }
        if (this.time > 2400){

            if (level() instanceof ServerLevel serverLevel) {
                this.kill(serverLevel);
            }
        }
        if (this.getOwner()!= null&&this.getOwner().getAttribute(Attributes.ARMOR)!=null){
            this.getAttributes().addTransientAttributeModifiers(AttributeModifier(this,(this.getOwner())));
        }
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            VibrationSystem.Ticker.tick(serverlevel, this.vibrationData, this.vibrationUser);
            if (this.isPersistenceRequired() || this.requiresCustomPersistence()) {
                WardenAi.setDigCooldown(this);
            }
        }
        {
            Vec3 playerPos = this.position().add(0, 0.75, 0);
            int range = 10;
            List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Mob mob : entities) {
                Identifier entity = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
                if (!entity.getNamespace().equals(Moonstone.MODID)) {
                    if (this.getTarget() == null){
                        this.setAttackTarget(mob);
                        break;
                    }
                }
            }
        }
        super.tick();
        if (this.level().isClientSide()) {
            if (this.tickCount % this.getHeartBeatDelay() == 0) {
                this.heartAnimation = 10;
                if (!this.isSilent()) {
                    sou();
                }
            }

            this.tendrilAnimationO = this.tendrilAnimation;
            if (this.tendrilAnimation > 0) {
                --this.tendrilAnimation;
            }

            this.heartAnimationO = this.heartAnimation;
            if (this.heartAnimation > 0) {
                --this.heartAnimation;
            }

            switch (this.getPose()) {
                case EMERGING:
                    this.clientDiggingParticles(this.emergeAnimationState);
                    break;
                case DIGGING:
                    this.clientDiggingParticles(this.diggingAnimationState);
            }
        }

    }

    protected void customServerAiStep(ServerLevel level) {
        ServerLevel serverlevel = (ServerLevel) this.level();
        super.customServerAiStep(level);
        if (this.tickCount % 20 == 0) {
            this.angerManagement.tick(serverlevel, this::canTargetEntity);
        }
    }
    public void handleEntityEvent(byte p_219360_) {
        if (p_219360_ == 4) {
            this.roarAnimationState.stop();
            this.attackAnimationState.start(this.tickCount);
        } else if (p_219360_ == 61) {
            this.tendrilAnimation = 10;
        } else if (p_219360_ == 62) {
            this.sonicBoomAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(p_219360_);
        }

    }

    public int getHeartBeatDelay() {
        float f = (float)this.getClientAngerLevel() / (float) AngerLevel.ANGRY.getMinimumAnger();
        return 40 - Mth.floor(Mth.clamp(f, 0.0F, 1.0F) * 30.0F);
    }


    public void clientDiggingParticles(AnimationState state) {
        if ((float)state.getTimeInMillis((float)this.tickCount) < 4500.0F) {
            RandomSource randomsource = this.getRandom();
            BlockState blockstate = this.getBlockStateOn();
            if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                for(int i = 0; i < 30; ++i) {
                    double d0 = this.getX() + (double)Mth.randomBetween(randomsource, -0.7F, 0.7F);
                    double d1 = this.getY();
                    double d2 = this.getZ() + (double)Mth.randomBetween(randomsource, -0.7F, 0.7F);
                    this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_219422_) {
        if (DATA_POSE.equals(p_219422_)) {
            switch (this.getPose()) {
                case EMERGING:
                    this.emergeAnimationState.start(this.tickCount);
                    break;
                case DIGGING:
                    this.diggingAnimationState.start(this.tickCount);
                    break;
                case ROARING:
                    this.roarAnimationState.start(this.tickCount);
                    break;
                case SNIFFING:
                    this.sniffAnimationState.start(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(p_219422_);
    }

    public boolean ignoreExplosion() {
        return this.isDiggingOrEmerging();
    }

    @Contract("null->false")
    public boolean canTargetEntity(@javax.annotation.Nullable Entity p_219386_) {
        if (p_219386_!=null && getOwner() != null) {
            if (p_219386_ == getOwner()) {
                return false;
            }
        }else {
            return false;
        }
        if (p_219386_ instanceof LivingEntity livingentity) {
            if (this.level() == p_219386_.level() && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(p_219386_) && !this.isAlliedTo(p_219386_) && !livingentity.isInvulnerable() && !livingentity.isDeadOrDying() && this.level().getWorldBorder().isWithinBounds(livingentity.getBoundingBox())) {
                return true;
            }
        }

        return false;
    }

    public void playListeningSound() {
        if (!this.hasPose(Pose.ROARING)) {
            this.playSound(this.getAngerLevel().getListeningSound(), 10.0F, this.getVoicePitch());
        }

    }

    public AngerLevel getAngerLevel() {
        return AngerLevel.byAnger(this.getActiveAnger());
    }
    AngerManagement angerManagement = new AngerManagement(this::canTargetEntity, Collections.emptyList());

    public int getActiveAnger() {
        return this.angerManagement.getActiveAnger(this.getTarget());
    }



    public boolean removeWhenFarAway(double p_219457_) {
        return false;
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        boolean flag = super.hurtServer(level, source,damage   );
        Entity entity = source.getEntity();
        if (entity instanceof LivingEntity living){
            this.setAttackTarget(living);
        }
        return flag;
    }


    public void setAttackTarget(LivingEntity p_219460_) {
        this.setTarget(p_219460_);
        SonicBoom.setCooldown(this, 200);
    }



    public boolean isPushable() {
        return !this.isDiggingOrEmerging() && super.isPushable();
    }


    @VisibleForTesting
    public AngerManagement getAngerManagement() {
        return this.angerManagement;
    }

    protected PathNavigation createNavigation(Level p_219396_) {
        return new GroundPathNavigation(this, p_219396_) {
            protected PathFinder createPathFinder(int p_219479_) {
                this.nodeEvaluator = new WalkNodeEvaluator();
                this.nodeEvaluator.setCanPassDoors(true);
                return new PathFinder(this.nodeEvaluator, p_219479_) {
                    protected float distance(Node p_219486_, Node p_219487_) {
                        return p_219486_.distanceToXZ(p_219487_);
                    }
                };
            }
        };
    }

    public VibrationSystem.Data getVibrationData() {
        return this.vibrationData;
    }

    public VibrationSystem.User getVibrationUser() {
        return this.vibrationUser;
    }

    public void increaseAngerAt(@javax.annotation.Nullable Entity p_219442_) {
        this.increaseAngerAt(p_219442_, 35, true);
    }

    @VisibleForTesting
    public void increaseAngerAt(@javax.annotation.Nullable Entity p_219388_, int p_219389_, boolean p_219390_) {
        if (!this.isNoAi() && this.canTargetEntity(p_219388_)) {
            WardenAi.setDigCooldown(this);
            boolean flag = !(this.getTarget() instanceof Player);
            int i = this.angerManagement.increaseAnger(p_219388_, p_219389_);
            if (p_219388_ instanceof Player living && flag && AngerLevel.byAnger(i).isAngry()) {
                if (this.getOwner() != null && !this.getOwner().is(p_219388_)) {
                    this.setTarget(living);
                }
            }
            if (p_219390_) {
                this.playListeningSound();
            }
        }

    }

    public class VibrationUser implements VibrationSystem.User {
        public static final int GAME_EVENT_LISTENER_RANGE = 16;
        public final PositionSource positionSource = new EntityPositionSource(CellGiant.this, CellGiant.this.getEyeHeight());
        public int getListenerRadius() {
            return 16;
        }
        public PositionSource getPositionSource() {
            return this.positionSource;
        }
        public boolean canTriggerAvoidVibration() {
            return true;
        }
        public boolean canReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, Holder<GameEvent> holder, GameEvent.Context context) {
            if (!CellGiant.this.isNoAi() && !CellGiant.this.isDeadOrDying() && !CellGiant.this.isDiggingOrEmerging() && serverLevel.getWorldBorder().isWithinBounds(blockPos)) {
                Entity entity = context.sourceEntity();
                if (entity instanceof LivingEntity) {
                    LivingEntity livingentity = (LivingEntity)entity;
                    if (!CellGiant.this.canTargetEntity(livingentity)) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        }

        public void onReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, Holder<GameEvent> holder, @org.jspecify.annotations.Nullable Entity entity, @org.jspecify.annotations.Nullable Entity entity1, float v) {
        }
    }
}
