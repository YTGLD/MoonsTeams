package com.ytgld.moonstone.enttiy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.List;

public class SwordOfTwelve extends Arrow {

    public SwordOfTwelve(EntityType<? extends SwordOfTwelve> entityType, Level level) {
        super(entityType, level);
        this.setXRot(Mth.nextInt(RandomSource.create(),0,360));
    }
    private final List<Vec3> trailPositions = new ArrayList<>();
    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
    @Override
    protected void onHitEntity(EntityHitResult result) {
    }
    private LivingEntity target;

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public LivingEntity getTarget() {
        return target;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (this.getOwner()!=null && this.getOwner() instanceof ServerPlayer player) {
            BlockPos blockpos = new BlockPos(this.blockPosition().getX(),this.blockPosition().getY()-2,this.blockPosition().getX());
            BlockState blockstate = this.level().getBlockState(blockpos);

            int i = 150;
            if (player.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), (int) this.position().x, (int) this.position().y, (int) this.position().z, i, 1.25, 1.25, 1.25, 0.15000000596046448);
            }
            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, player.getSoundSource(), 0.2F, 0.2F
            );}
        Vec3 position = this.position();
        float is = 5;
        List<LivingEntity> ess = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
        for (LivingEntity es : ess){
            if (!es.is(this.getOwner())&&this.getOwner() instanceof Player player){
                es.invulnerableTime = 0;
                es.hurt(player.damageSources().dryOut(), (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE)*2);
            }
        }

    }

    @Override
    public void playerTouch(Player entity) {

    }

    @Override
    public void tick() {
        super.tick();
        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 50) {
            trailPositions.remove(0);
        }
        if (tickCount>=600) {
            this.discard();
        }
    }
}

