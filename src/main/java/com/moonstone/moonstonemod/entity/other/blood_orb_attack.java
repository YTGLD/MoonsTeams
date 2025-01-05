package com.moonstone.moonstonemod.entity.other;

import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class blood_orb_attack extends ThrowableItemProjectile {

    private final List<Vec3> trailPositions = new ArrayList<>();

    public blood_orb_attack(EntityType<? extends blood_orb_attack> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);

    }


    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.blood.get();
    }

    @Override
    public float getYRot() {
        return 0;
    }

    @Override
    public float getXRot() {
        return 0;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getOwner()!=null) {
            float xRot = -this.getOwner().getXRot();
            float yRot = -this.getOwner().getYRot();

            double radX = Math.toRadians(xRot);
            double radY = Math.toRadians(yRot);
            float directionX = (float) (Math.sin(radY) * Math.cos(radX));
            float directionY = (float) (Math.sin(radX));
            float directionZ = (float) (Math.cos(radY) * Math.cos(radX));

            float speed = 0.2f;
            this.setPos(this.getX() + directionX * speed, this.getY() + directionY * speed, this.getZ() + directionZ * speed);
        }
        this.setXRot(0);
        this.setYRot(0);
        this.setYBodyRot(0);
        if (this.tickCount > 200) {
            this.discard();
        }

        if (this.tickCount % 10 == 0) {
            blood_orb_small attack_blood = new blood_orb_small(EntityTs.blood_orb_small.get(), this.level());
            attack_blood.setPos(this.position());
            attack_blood.setOwner(this.getOwner());

            this.level().addFreshEntity(attack_blood);

        }

        trailPositions.add(new Vec3(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 50) {
            trailPositions.remove(0);
        }

        this.setNoGravity(true);
    }

}


