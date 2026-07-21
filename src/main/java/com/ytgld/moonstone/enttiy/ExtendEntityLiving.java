package com.ytgld.moonstone.enttiy;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.Moonstone;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class ExtendEntityLiving extends TamableAnimal {
    protected ExtendEntityLiving(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount<=10){
            this.heal(1000);
        }
        if (this.getOwner()!=null&&this.getTarget()!=null){
            if (this.getTarget().is(this.getOwner())){
                this.setTarget(null);
            }
        }
        if (this.getOwner()!=null &&this.getOwner() instanceof Player player){
//
//            if (Handler.hascurio(player, BookItems.detect.get())){
//                if (this.getTarget()!=null&&!this.getTarget().is(this.getOwner())){
//                    this.getTarget().addEffect(new MobEffectInstance(MobEffects.GLOWING,100,0));
//                }
//            }
//            if (Handler.hascurio(player,BookItems.exercise_reinforcement.get())){
//                this.getAttributes().addTransientAttributeModifiers(moveTag(player));
//            }
        }
        if (this.getOwner()!= null) {
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
//        if (this.entityTags().contains(BookItems.tumourTAG)){
//            this.getAttributes().addTransientAttributeModifiers(tumourTAG());
//        }
//        if (this.entityTags().contains(BookItems.bone_structureTAG)){
//            this.getAttributes().addTransientAttributeModifiers(bone_structureTAG(this.getOwner()));
//        }
//
//        if (this.entityTags().contains(BookItems.mummificationTAG)){
//            this.getAttributes().addTransientAttributeModifiers(mummificationTAG());
//        }
//        if (this.entityTags().contains(BookItems.organizational_regenerationTAG)) {
//            if (this.tickCount % 5 == 1) {
//                this.heal(1);
//            }
//        }
    }
    private Multimap<Holder<Attribute>, AttributeModifier> tumourTAG(){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                Identifier.parse(this.stringUUID),
                -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                Identifier.parse(this.stringUUID),
                1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }

    private Multimap<Holder<Attribute>, AttributeModifier> bone_structureTAG(LivingEntity owner){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (owner != null) {

            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(
                    Identifier.parse(this.stringUUID),
                    owner.getAttributeValue(Attributes.ARMOR) * 0.7f, AttributeModifier.Operation.ADD_VALUE));

            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                    Identifier.parse(this.stringUUID),
                    owner.getAttributeValue(Attributes.MAX_HEALTH)*0.3F, AttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    private Multimap<Holder<Attribute>, AttributeModifier> mummificationTAG(){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                Identifier.parse(this.stringUUID),
                -0.2, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }

    private Multimap<Holder<Attribute>, AttributeModifier> moveTag(Player player){
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                Identifier.parse(this.stringUUID),
                player.getAttributeValue(Attributes.MOVEMENT_SPEED)*0.6F, AttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }

}
