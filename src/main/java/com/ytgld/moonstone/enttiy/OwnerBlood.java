package com.ytgld.moonstone.enttiy;

import com.mojang.datafixers.kinds.IdF;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.blood.magic.BloodCandle;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OwnerBlood extends TamableAnimal {
    public OwnerBlood(EntityType<? extends OwnerBlood> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
        this.setNoGravity(true);
    }
    @Override
    public void die(@NotNull DamageSource p_21809_) {

    }
    protected boolean isAffectedByBlocks() {
        return !this.isRemoved();
    }
    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);

        {
            Vec3 playerPos = this.position();
            int range = 10;
            List<OwnerBlood> imperialHematomas = this.level().getEntitiesOfClass(OwnerBlood.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (OwnerBlood imperialHematoma : imperialHematomas){
                if (imperialHematoma.getOwner()!= null &&this.getOwner()!=null) {
                    if (!imperialHematoma.is(this)){
                        if (imperialHematoma.getOwner().is(this.getOwner())){
                            imperialHematoma.discard();
                            return;
                        }
                    }
                }
            }
        }

        LivingEntity owner = getOwner(); // 获取主人
        LivingEntity target = getTarget(); // 获取目标
        Vec3 currentPos = this.position();

        if (owner != null){
            double desiredDistance = 2; // 设置想要保持的距离
            Vec3 targetPos = owner.position().add(0, 3, 1); // 获取玩家位置并抬高

            Vec3 forward = owner.getLookAngle(); // 获取玩家的朝向向量
            Vec3 direction = forward.scale(-1).normalize(); // 计算背后的方向（逆向）

            Vec3 newTargetPos = targetPos.add(direction.scale(desiredDistance)); // 计算新的目标位置

            this.teleportTo(newTargetPos.x, newTargetPos.y, newTargetPos.z);
        }

        if (this.getOwner() != null) {
            if (this.getOwner() instanceof Player player){
                if (!Handler.hascurio(player, Items.blood_candle.get())){
                    this.discard();
                }
            }
        }
        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 150) {
            trailPositions.remove(0);
        }


        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 20;
        List<Mob> entities = this.level().getEntitiesOfClass(Mob.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (Mob mob : entities) {
            if (this.getTarget() == null) {
                Identifier entity = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
                if (!entity.getNamespace().equals(Moonstone.MODID)) {
                    this.setTarget(mob);
                }
            }
        }
        if (this.getTarget() != null&&this.getOwner()!=null){
            if (this.getTarget().is(this.getOwner())){
                this.setTarget(null);
            }
        }
        if (this.getTarget() != null) {
            if (!this.getTarget().isAlive()) {
                this.setTarget(null);
            }
        }
        if (this.getOwner()!= null) {

            if (this.getOwner().getLastHurtByMob() != null&&isMoon(this.getOwner().getLastHurtByMob())) {
                if (!this.getOwner().getLastHurtByMob().is(this)) {
                    this.setTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker() != null&&isMoon(this.getOwner().getLastAttacker())) {
                if (!this.getOwner().getLastAttacker().is(this)) {
                    this.setTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob() != null&&isMoon(this.getOwner().getLastHurtMob())) {
                if (!this.getOwner().getLastHurtMob().is(this)) {
                    this.setTarget(this.getOwner().getLastHurtMob());
                }
            }
        }
        float s = 20;
        if (this.getOwner()!= null &&this.getOwner() instanceof Player player){
            if (Handler.hascurio(player,Items.owner_blood_eye.get()) || Handler.hascurio(player,Items.the_blood_book.get())){
                s*=0.8f;
            }
            if (Handler.hascurio(player,Items.owner_blood_attack_eye.get())){
                s*=1.1f;
            }
            if (Handler.hascurio(player,Items.owner_blood_speed_eye.get()) || Handler.hascurio(player,Items.the_blood_book.get())){
                s*=0.5f;
            }
            if (Handler.hascurio(player,Items.owner_blood_boom_eye.get())){
                s*= 3;
            }
            if (Handler.hascurio(player,Items.the_blood_book.get())){
                s *= 0.5f;
            }
        }
        if (this.getOwner()!= null &&this.getOwner() instanceof Player player&&this.getTarget()!=null){
            if (this.tickCount % (int) s == 0) {
                if (!Handler.hascurio(player,Items.consciousness.asItem())){
                    if (!Handler.hascurio(player, Items.owner_blood_earth.get())) {
                        AttackBlood attackBlood = new AttackBlood(EntityTs.attack_blood_.get(), this.level());

                        attackBlood.setTarget(this.getTarget());

                        addSuperAttackBlood(attackBlood,player);

                        attackBlood.setPos(this.position());
                        attackBlood.setOwner(this.getOwner());

                        this.level().addFreshEntity(attackBlood);


                        playRemoveOneSound(this);

                    }
                }
            }
            if (Handler.hascurio(player,Items.owner_blood_earth.get())){
                {
                    Vec3 position = this.position();
                    int is = 12;
                    List<Entity> ess = this.level().getEntitiesOfClass(Entity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
                    for (Entity es : ess) {
                        Identifier entity = BuiltInRegistries.ENTITY_TYPE.getKey(es.getType());
                        if (!entity.getNamespace().equals(Moonstone.MODID) && es != this.getOwner() && !es.is(this)) {
                            if (es instanceof ItemEntity item) {
                                Vec3 motion = position.subtract(item.position().add(0, item.getBbHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                item.setDeltaMovement(motion.scale(1));
                            }
                            if (es instanceof LivingEntity item) {
                                Vec3 motion = position.subtract(item.position().add(0, item.getBbHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                item.setDeltaMovement(motion.scale(0.25));
                            }
                            if (es instanceof Projectile item) {
                                Vec3 motion = position.subtract(item.position().add(0, item.getBbHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                item.setDeltaMovement(motion.scale(3));
                            }
                        }
                    }
                }
                {
                    Vec3 added = this.position().add(0, 0.75, 0);
                    int r = 1;
                    List<Projectile> ss = this.level().getEntitiesOfClass(Projectile.class, new AABB(added.x - r, added.y - r, added.z - r, added.x + r, added.y + r, added.z + r));
                    for (Projectile entity : ss){
                        entity.discard();
                        player.heal(4);
                    }
                }
            }
        }
        if ( target != null) {
            if (currentPos.distanceTo(target.position()) > 30) {
                this.setTarget(null);
            }
        }
        clear();
    }

    public static void addSuperAttackBlood(AttackBlood attackBlood,Player player){
        if (Handler.hascurio(player, Items.owner_blood_speed_eye.get()) || Handler.hascurio(player,Items.the_blood_book.get())) {
            attackBlood.setCannotFollow(false);
            attackBlood.setSpeed(attackBlood.getSpeeds() * 4);
        }
        if (Handler.hascurio(player, Items.the_blood_book.get())) {
            attackBlood.setSpeed(attackBlood.getSpeeds()*2f);
            attackBlood.maxTime = attackBlood.maxTime * 0.25f;
            attackBlood.setDamage(attackBlood.getDamages()*3f);
            attackBlood.isPlayer = true;
        }
        if (Handler.hascurio(player, Items.owner_blood_attack_eye.get()) || Handler.hascurio(player,Items.the_blood_book.get())) {
            attackBlood.setDamage(attackBlood.getDamages() * 1.2f);
        }
        if (Handler.hascurio(player, Items.owner_blood_effect_eye.get()) || Handler.hascurio(player,Items.the_blood_book.get())) {
            attackBlood.setEffect(true);
        }
        if (Handler.hascurio(player, Items.owner_blood_vex.get()) || Handler.hascurio(player,Items.the_blood_book.get())) {
            attackBlood.setHeal(true);
            attackBlood.setDamage(attackBlood.getDamages() - 3);
        }
        if (Handler.hascurio(player, Items.owner_blood_boom_eye.get())) {
            attackBlood.setSpeed(attackBlood.getSpeeds() * 0.8f);
            attackBlood.setBoom(true);
        }
    }
    private boolean isMoon(LivingEntity living){
        if (living != null){
            Identifier name = BuiltInRegistries.ENTITY_TYPE.getKey(living.getType());
            return !name.getNamespace().equals(Moonstone.MODID);
        }
        return  true;
    }
    public boolean canLive = true;
    private void clear(){
        if (canLive) {
            if (this.getOwner() != null && this.getOwner() instanceof Player player) {
                if (!player.level().isClientSide()) {
                    canLive = true;
                    CompoundTag compoundTag = player.getPersistentData();
                    if (!compoundTag.getBooleanOr(BloodCandle.hasOwnerBlood, false)) {
                        canLive = false;
                    }
                }
            }
        }
        if (!canLive){
            this.discard();
        }
    }
    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), 0.8F, 0.8F + p_186343_.level().getRandom().nextFloat() * 0.4F);
    }
    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }
    public boolean isPushable() {
        return false;
    }


    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
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
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;

    }
}
