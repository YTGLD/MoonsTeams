package com.moonstone.moonstonemod.item.man;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.ExtendEntityLiving;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

/**
 * 玩家本体伤害降低20%
 * <p>
 * 召唤的僵尸继承玩家50%的攻击力
 * <p>
 * 召唤的僵尸伤害提高30%
 */
public class muscle_conversion extends ManDNA implements Iplague {
    public muscle_conversion() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public @Nullable List<Item> getDrug() {
        return List.of();
    }
    public  static void zombieAttack(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof ExtendEntityLiving moonTamableAnimal){
            if (moonTamableAnimal.getOwner() instanceof Player player){
                if (Handler.hascurio(player, Items.muscle_conversion.get())) {
                    float zombieDamage = (float) player.getAttributeValue(AttReg.zombie_attack_damage.get());
                    moonTamableAnimal.getAttributes().addTransientAttributeModifiers(muscle_conversion.modifierMultimap(player,moonTamableAnimal));
                    event.setAmount(event.getAmount()*zombieDamage);

                }
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
         Multimap<Attribute, AttributeModifier> modifierMultimap= HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(uuid,"a",
                        -0.2f, AttributeModifier.Operation.MULTIPLY_BASE));
        return modifierMultimap;
    }


    public static  Multimap<Attribute, AttributeModifier> modifierMultimap (Player owner,ExtendEntityLiving moonTamableAnimal){
         Multimap<Attribute, AttributeModifier> modifierMultimap= HashMultimap.create();
        float damage = (float) (owner.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.5f);
        modifierMultimap.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(moonTamableAnimal.getUUID(),"",
                        damage, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.muscle_conversion.tool.string").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.muscle_conversion.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.muscle_conversion.tool.string.2").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }
}
