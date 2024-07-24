package com.moonstone.moonstonemod.entity.bloodvruis;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.necora.test_e;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class blood_bat extends TamableAnimal {
    public blood_bat(EntityType<? extends blood_bat> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }
    public int time = 0;


    public Multimap<Attribute, AttributeModifier> getAtt(UUID uuid) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID+":cell_immortal", 10, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
    public Multimap<Attribute, AttributeModifier> Rage(UUID uuid) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID+":cell_immortal", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifierMultimap;
    }
    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        if (this.getTags().contains(bat_cell.cell_immortal)){
            this.getAttributes().addTransientAttributeModifiers(getAtt(this.uuid));
        }
        if (this.getTags().contains(bat_cell.cell_rage)){
            this.getAttributes().addTransientAttributeModifiers(Rage(this.uuid));

        }
        if (this.getTags().contains(bat_cell.cell_fear)){
            time++;
        }else {
            time+=2;
        }
        if (time>1200){
            this.kill();
        }
        if (this.tickCount%20 == 0) {
            Vec3 position = this.position();
            int is = 18;
            List<LivingEntity> items = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
            for (LivingEntity livingEntity : items) {

                ResourceLocation name = ForgeRegistries.ENTITY_TYPES.getKey(livingEntity.getType());
                if (name!= null) {
                    if (!name.getNamespace().equals(MoonStoneMod.MODID)) {
                        if (this.getOwner() != null && !this.getOwner().is(livingEntity)&&this.getTarget()!=null) {

                            if (this.getTarget().is(livingEntity)) {

                                Vec3 vec3 = this.position().add(0.0D, 0.35, 0.0D);
                                Vec3 vec31 = livingEntity.getEyePosition().subtract(vec3);
                                Vec3 vec32 = vec31.normalize();

                                if (Mth.floor(vec31.length()) < 5) {
                                    for (int i = 1; i < Mth.floor(vec31.length()) + 10; ++i) {
                                        Vec3 vec33 = vec3.add(vec32.scale(i));

                                        test_blood z = new test_blood(EntityTs.test_blood.get(), this.level());

                                        z.teleportTo(vec33.x, vec33.y - 1, vec33.z);
                                        z.setNoAi(true);
                                        z.setNoGravity(true);
                                        z.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 12000, 0, false, false));

                                        this.level().addFreshEntity(z);
                                    }
                                    if (this.getAttribute(Attributes.ATTACK_DAMAGE)!= null) {
                                        if (this.getTags().contains(bat_cell.cell_doctor)) {
                                            this.heal((float) (this.getAttribute(Attributes.ATTACK_DAMAGE).getValue() / 5));
                                        }
                                        if (livingEntity instanceof Mob mob){
                                            if (this.getTags().contains(bat_cell.cell_desecrate)) {
                                                if (!mob.isInvertedHealAndHarm()) {
                                                    mob.hurt(this.damageSources().sonicBoom(this), (float) (1 + this.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 1.4f));
                                                    mob.invulnerableTime = 0;
                                                }
                                            }
                                        }
                                        if (this.getTags().contains(bat_cell.cell_blood_attack)){
                                            this.getOwner().heal((float) (this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()/10));
                                        }
                                        livingEntity.hurt(this.damageSources().sonicBoom(this), (float) (1 + this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
                                        livingEntity.invulnerableTime = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.BAT_HURT;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ARMOR,2);
    }

    @Override
    public void die(@NotNull DamageSource p_21809_) {

    }
    public boolean isPushable() {
        return false;
    }

    protected void doPush(Entity p_27415_) {
    }

    @Override
    public boolean hurt(DamageSource p_27567_, float p_27568_) {
        if (p_27567_.is(DamageTypes.FALL)){
            return false;
        }
        return super.hurt(p_27567_, p_27568_);
    }

    protected void pushEntities() {
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
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Zombie.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Spider.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Skeleton.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Creeper.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, EnderMan.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }
    public static double calculateDistance(LivingEntity entity1, LivingEntity entity2) {
        Vec3 pos1 = entity1.position();
        Vec3 pos2 = entity2.position();

        double dx = pos1.x - pos2.x;
        double dy = pos1.y - pos2.y;
        double dz = pos1.z - pos2.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    protected void customServerAiStep() {
        super.customServerAiStep();
        if ( this.getTarget()!= null) {
            float m  = (float) calculateDistance(this.getTarget() ,this);
            if (m>4) {
                double d2 = this.getTarget().getX() + 0.5D - this.getX();
                double d0 = this.getTarget().getY() + 0.1D - this.getY();
                double d1 = this.getTarget().getZ() + 0.5D - this.getZ();
                Vec3 vec3 = this.getDeltaMovement();
                Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double) 0.1F, (Math.signum(d0) * (double) 0.7F - vec3.y) * (double) 0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double) 0.1F);
                this.setDeltaMovement(new Vec3(vec31.x,vec31.y*1.1,vec31.z));
                float f = (float) (Mth.atan2(vec31.z, vec31.x) * (double) (180F / (float) Math.PI)) - 90.0F;
                float f1 = Mth.wrapDegrees(f - this.getYRot());
                this.zza = 0.5F;
                this.setYRot(this.getYRot() + f1);
            }else {
                double d2 = this.getTarget().getX() + 0.5D - this.getX();
                double d0 = this.getTarget().getY() + 0.1D - this.getY();
                double d1 = this.getTarget().getZ() + 0.5D - this.getZ();
                Vec3 vec3 = this.getDeltaMovement();
                Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double) 0.1F, (Math.signum(d0) * (double) 0.7F - vec3.y) * (double) 0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double) 0.1F);
                this.setDeltaMovement(new Vec3(-vec31.x,-vec31.y,-vec31.z));

            }
        }

    }
}
