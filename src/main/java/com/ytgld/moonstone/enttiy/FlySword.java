package com.ytgld.moonstone.enttiy;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.item.ms.nanodoom.Million;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class FlySword extends Entity {
    public FlySword(EntityType<? extends FlySword> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    public boolean alwaysAttack(){
        return false;
    }

    private LivingEntity target;
    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    private int coll;
    @Override
    public void tick() {
        this.baseTick();
        this.noPhysics = true;
        Vec3 playerPos = this.position().add(0, 0.75, 0);
        int range = 1;
        this.setNoGravity(true);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
        for (LivingEntity entity : entities) {
            if (this.getOwner() != null) {
                if (!entity.is(this.getOwner()) && this.getOwner() instanceof Player player) {
                    if (alwaysAttack()) {
                        if (this.tickCount > 10 && coll <= 0) {
                            if (player.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                                entity.invulnerableTime = 0;
                                entity.knockback(0.1f, Mth.nextFloat(RandomSource.create(), -0.1f, 0.1f), Mth.nextFloat(RandomSource.create(), -0.1f, 0.1f));
                                entity.hurt(entity.damageSources().magic(),
                                        (float) (0.3f + player.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 0.07f));
                                coll = 20;
                            }
                        }
                    }else {
                        if (this.tickCount > 10) {
                            if (player.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                                entity.invulnerableTime = 0;
                                entity.knockback(0.1f, Mth.nextFloat(RandomSource.create(), -0.1f, 0.1f), Mth.nextFloat(RandomSource.create(), -0.1f, 0.1f));
                                entity.hurt(entity.damageSources().magic(),
                                        (float) (0.3f + player.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 0.07f));
                                this.discard();
                            }
                        }
                    }
                }
            }
        }
        if (coll > 0) {
            coll --;
        }
        this.move(
                MoverType.SELF,
                this.getDeltaMovement()
        );


        // =========================
        // 你的原追踪逻辑放这里
        // =========================


        if (target != null && target.isAlive()) {
            if (tickCount > 200 && owner!=null) {

                Vec3 targetPos = owner.position();

                Vec3 direction =
                        targetPos.subtract(position())
                                .normalize();

                setDeltaMovement(
                        direction.scale(0.58f)
                );
                for (LivingEntity entity : entities){
                    if (entity.is(owner)) {
                        this.discard();
                    }
                }
            }
            if (tickCount > 30 && tickCount < 200) {

                Vec3 targetPos = target.position();

                Vec3 direction =
                        targetPos.subtract(position())
                                .normalize();


                Vec3 current =
                        getDeltaMovement().normalize();


                double angle =
                        Math.acos(
                                current.dot(direction)
                        );


                if (angle > Math.toRadians(25)) {

                    double limit =
                            Math.toRadians(25);


                    Vec3 newDir =
                            current.scale(Math.cos(limit))
                                    .add(
                                            direction.scale(Math.sin(limit))
                                    );


                    setDeltaMovement(
                            newDir.scale(0.58f)
                    );

                } else {

                    setDeltaMovement(
                            direction.scale(0.58f)
                    );
                }
            }
        }

        //轨迹
        if(canSee){
            trailPositions.add(position());
        }


        if(trailPositions.size()>15){
            trailPositions.removeFirst();
        }


        if(!canSee){
            live--;
        }


        if(live<=0){
            discard();
        }


        tickCount++;
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    public int live = 50;

    public boolean canSee = true;

    public Entity owner;
    private Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }
    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(0.01f, 0.01f);
    }
    @Override
    protected void readAdditionalSaveData(ValueInput input) {

    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {

    }
}
