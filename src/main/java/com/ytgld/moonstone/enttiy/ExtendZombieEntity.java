package com.ytgld.moonstone.enttiy;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.event.DNAModifyHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class ExtendZombieEntity extends TamableAnimal {
    protected ExtendZombieEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }
    public int time = 0;

    @Override
    public void tick() {
        super.tick();
        DNAModifyHandler.addZombieAttribute(this);


        if (this.tickCount <= 10) {
            this.heal(1000);
        }
        if (this.getOwner() != null && this.getTarget() != null) {
            if (this.getTarget().is(this.getOwner())) {
                this.setTarget(null);
            }
        }
        if (this.getOwner() != null) {
            if (this.getOwner().getLastHurtByMob() != null) {
                if (!this.getOwner().getLastHurtByMob().is(this) && !BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastHurtByMob().getType()).getNamespace().equals(Moonstone.MODID)) {
                    this.setTarget(this.getOwner().getLastHurtByMob());
                }
            }
            if (this.getOwner().getLastAttacker() != null) {
                if (!this.getOwner().getLastAttacker().is(this) && !BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastAttacker().getType()).getNamespace().equals(Moonstone.MODID)) {
                    this.setTarget(this.getOwner().getLastAttacker());
                }

            }
            if (this.getOwner().getLastHurtMob() != null) {
                if (!this.getOwner().getLastHurtMob().is(this) && !BuiltInRegistries.ENTITY_TYPE.getKey(this.getOwner().getLastHurtMob().getType()).getNamespace().equals(Moonstone.MODID)) {
                    this.setTarget(this.getOwner().getLastHurtMob());
                }

            }
        }
    }
    private Multimap<Holder<Attribute>, AttributeModifier> moveTag(Player player){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                ResourceLocation.parse(this.stringUUID),
                player.getAttributeValue(Attributes.MOVEMENT_SPEED)*0.6F, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }

}
