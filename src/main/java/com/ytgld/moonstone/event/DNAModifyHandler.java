package com.ytgld.moonstone.event;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.ItemBase;
import com.ytgld.moonstone.Moonstone;
import com.ytgld.moonstone.enttiy.ExtendZombieEntity;
import com.ytgld.moonstone.item.ICanHasInItem;
import com.ytgld.moonstone.item.IDNASequence;
import com.ytgld.moonstone.item.InitItems;
import com.ytgld.moonstone.other.AttReg;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.List;

public class DNAModifyHandler {
    public static void doStrongerDNAModifiers(ItemBase itemBase, ItemStack stack, LivingEntity livingEntity,
                                              Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap) {
        doModifiers(itemBase, stack, livingEntity, attributeModifierMultimap);
        doDNAAttributes(itemBase, stack, livingEntity, attributeModifierMultimap);
    }
    private static void doModifiers(ItemBase itemBase, ItemStack stack, LivingEntity livingEntity,
                                    Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap){
        itemBase.modifyAttribute(stack, InitItems.regenerative.asItem(), AttReg.heal,new AttributeModifier(
                itemBase.id(stack),0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.autolytic.asItem(), NeoForgeMod.SWIM_SPEED,new AttributeModifier(
                itemBase.id(stack),0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.atpoverdose.asItem(), Attributes.MAX_HEALTH,new AttributeModifier(
                itemBase.id(stack),4, AttributeModifier.Operation.ADD_VALUE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.fermentation.asItem(), AttReg.cit,new AttributeModifier(
                itemBase.id(stack),0.13, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.putrefactive.asItem(), Attributes.ARMOR,new AttributeModifier(
                itemBase.id(stack),0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.ambush.asItem(), Attributes.LUCK,new AttributeModifier(
                itemBase.id(stack),2, AttributeModifier.Operation.ADD_VALUE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.quadriceps.asItem(), Attributes.MOVEMENT_SPEED,new AttributeModifier(
                itemBase.id(stack),0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.calcification.asItem(), Attributes.ARMOR,new AttributeModifier(
                itemBase.id(stack),4, AttributeModifier.Operation.ADD_VALUE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.WarmApproachable.asItem(), Attributes.MOVEMENT_SPEED,new AttributeModifier(
                itemBase.id(stack),0.03, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.EarthAffinity.asItem(), Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(
                itemBase.id(stack),0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);

        itemBase.modifyAttribute(stack, InitItems.OceanAffinity.asItem(), NeoForgeMod.SWIM_SPEED,new AttributeModifier(
                itemBase.id(stack),0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        ),attributeModifierMultimap);
    }
    public static void doDNAAttributes(ItemBase itemBase, ItemStack stack, LivingEntity livingEntity,
                                              Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap){

    }

    public static void modifyComponent(List<Component> components,ItemStack stack ){
        if (stack.getItem() instanceof ItemBase itemBase) {
            if (itemBase.maxSize() > 0 || itemBase.maxDNAValue() > 0) {
                components.add(Component.translatable("item.modifiers.any").withStyle(ChatFormatting.GOLD));
            }
        }

        addDNASequenceComponent(stack, InitItems.cytopathic_boost.asItem(),Component.translatable("moonstone.cytopathic_boost.modify"),components);
        addDNASequenceComponent(stack, InitItems.spliced_activation.asItem(),Component.translatable("moonstone.spliced_activation.modify"),components);
        addDNASequenceComponent(stack, InitItems.thermo_necro.asItem(),Component.translatable("moonstone.thermo_necro.modify"),components);

        addModifyComponent(stack, InitItems.reanimation.asItem(),Component.translatable("moonstone.reanimation.modify"),components);
        addModifyComponent(stack, InitItems.polyphagia.asItem(),Component.translatable("moonstone.polyphagia.modify"),components);
        addModifyComponent(stack, InitItems.masticatory.asItem(),Component.translatable("moonstone.masticatory.modify"),components);


        addModifyComponent(stack, InitItems.calcareous.asItem(),Component.translatable("moonstone.calcareous.modify"),components);
        addModifyComponent(stack, InitItems.frontal_lobe.asItem(),Component.translatable("moonstone.frontal_lobe.modify"),components);
        addModifyComponent(stack, InitItems.high_energy.asItem(),Component.translatable("moonstone.high_energy.modify"),components);
        addModifyComponent(stack, InitItems.surge.asItem(),Component.translatable("moonstone.surge.modify"),components);

    }


    private static void addDNASequenceComponent(ItemStack stack, Item item, Component component, List<Component> components){
        if (stack.getItem() instanceof ItemBase) {
            if (IDNASequence.getAllDNA(stack).contains(item)) {
                components.add(Component.literal("+").append(component));
            }
        }
    }
    private static void addModifyComponent(ItemStack stack, Item item, Component component, List<Component> components){
        if (stack.getItem() instanceof ItemBase) {
            if (ICanHasInItem.getAll(stack).contains(item)) {
                components.add(Component.literal("+").append(component));
            }
        }
    }
    public static void addZombieAttribute(ExtendZombieEntity entity){
        if (entity.getOwner() instanceof Player player) {
            entity.getAttributes().addTransientAttributeModifiers(attributeAllZombie(player));
        }
    }
    private static Multimap<Holder<Attribute>, AttributeModifier> attributeAllZombie (Player player){
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap =  HashMultimap.create();
        //僵尸获得40%召唤者的伤害，速度，护甲
        if (IDNASequence.hasHowDNASequence(player, InitItems.cytopathic_boost.get())) {
            float damage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.4F;
            float armor = (float) player.getAttributeValue(Attributes.ARMOR) * 0.4F;
            float speed = (float) player.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.4F;

            attributeModifierMultimap.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(dnaID(InitItems.cytopathic_boost.asItem()),
                    damage, AttributeModifier.Operation.ADD_VALUE));

            attributeModifierMultimap.put(Attributes.ARMOR,new AttributeModifier(dnaID(InitItems.cytopathic_boost.asItem()),
                    armor, AttributeModifier.Operation.ADD_VALUE));

            attributeModifierMultimap.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(dnaID(InitItems.cytopathic_boost.asItem()),
                    speed, AttributeModifier.Operation.ADD_VALUE));
        }
        //僵尸的最大生命值提高50%，且快速恢复生命值
        if (IDNASequence.hasHowDNASequence(player, InitItems.spliced_activation.get())) {
            attributeModifierMultimap.put(Attributes.MAX_HEALTH,new AttributeModifier(dnaID(InitItems.spliced_activation.asItem()),
                    0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
        return attributeModifierMultimap;
    }
    @SubscribeEvent
    public void eatStart(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            if (event.getItem().getUseAnimation() == ItemUseAnimation.EAT) {
                if (Handler.hasModifyFormItem(player, InitItems.polyphagia.get())) {
                    player.heal(6);
                }
            }
        }
    }
    @SubscribeEvent
    public void eatStart(EntityTickEvent.Pre event) {
        //僵尸/宠物单位增加10点生命值且自然恢复
        if (event.getEntity() instanceof LivingEntity livingEntity &&  event.getEntity() instanceof OwnableEntity ownableEntity) {
            if (ownableEntity.getOwner() instanceof Player player) {
                livingEntity.getAttributes().addTransientAttributeModifiers(attributeModifierMultimap(player));
                if (Handler.hasModifyFormItem(player, InitItems.high_energy.get())){
                    if (livingEntity.tickCount % 20 == 1) {
                        livingEntity.heal(1);
                    }
                }
            }
        }
        //僵尸的最大生命值提高50%，且快速恢复生命值
        if (event.getEntity() instanceof ExtendZombieEntity zombie) {
            if (zombie.getOwner() instanceof Player player) {
                if (Handler.hasModifyFormItem(player, InitItems.spliced_activation.get())){
                    if (zombie.tickCount % 10 == 1) {
                        zombie.heal(1);
                    }
                }
                //降低60%僵尸的腐烂速度
                if (Handler.hasModifyFormItem(player, InitItems.thermo_necro.get())){
                    if (zombie.tickCount % 8 == 1) {
                        zombie.time = zombie.time - 1;
                    }
                }
            }
        }
    }
    private static Identifier modifyID(Item item){
        return Identifier.fromNamespaceAndPath(
                Moonstone.MODID,item.getDescriptionId() +
                        "_modify"
        );
    }
    private static Identifier dnaID(Item item){
        return Identifier.fromNamespaceAndPath(
                Moonstone.MODID,item.getDescriptionId() +
                        "_dna"
        );
    }
    private static Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap (Player player){
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap =  HashMultimap.create();
        //僵尸/宠物单位增加8点护甲
        if (Handler.hasModifyFormItem(player, InitItems.calcareous.get())) {
            attributeModifierMultimap.put(Attributes.ARMOR,new AttributeModifier(modifyID(
                    InitItems.calcareous.get()),
                    8, AttributeModifier.Operation.ADD_VALUE));
        }
        //僵尸/宠物单位增加30%速度
        if (Handler.hasModifyFormItem(player, InitItems.frontal_lobe.get())) {
            attributeModifierMultimap.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(modifyID(
                    InitItems.frontal_lobe.get()),
                    0.3F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        //僵尸/宠物单位增加10点生命值且自然恢复
        if (Handler.hasModifyFormItem(player, InitItems.high_energy.get())) {
            attributeModifierMultimap.put(Attributes.MAX_HEALTH,new AttributeModifier(modifyID(
                    InitItems.high_energy.get()),
                    10, AttributeModifier.Operation.ADD_VALUE));
        }
        //僵尸/宠物单位造成的伤害提高20%
        if (Handler.hasModifyFormItem(player, InitItems.surge.get())) {
            attributeModifierMultimap.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(modifyID(
                    InitItems.surge.get()),
                    0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return attributeModifierMultimap;
    }
    @SubscribeEvent
    public void eatEnt(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player){
            if (event.getItem().getUseAnimation() == ItemUseAnimation.EAT) {
                if (Handler.hasModifyFormItem(player, InitItems.masticatory.get())) {
                    event.setDuration((int) (event.getDuration() * 0.75f));
                }
            }
        }
    }
    @SubscribeEvent
    public void reanimation(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hasModifyFormItem(player, InitItems.reanimation.get())){
                if (!player.getCooldowns().isOnCooldown(InitItems.reanimation.get().getDefaultInstance())) {
                    if (event.getNewDamage() > player.getHealth()) {
                        event.setNewDamage(0);
                        player.heal(player.getMaxHealth() / 4);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 0.8F, 0.8F);
                        player.getCooldowns().addCooldown(InitItems.reanimation.get().getDefaultInstance(), 3000);
                    }
                }
            }
        }
    }
}
