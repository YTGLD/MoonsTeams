package com.moonstone.moonstonemod.item.BloodVirus.dna;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.bloodvruis.blood_bat;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class bat_cell extends BloodViru {
    public static final String cell_doctor="CellDoctor";
    public static final String cell_desecrate="CellDesecrate";
    public static final String cell_immortal="CellImmortal";
    public static final String cell_rage="CellRage";
    public static final String cell_blood_attack="CellBloodAttack";
    public static final String cell_fear="CellFear";

    public static void Bat(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player living) {
            if (Handler.hascurio(living,Items.bloodvirus.get())) {
                if (Handler.hascurio(living, Items.bat_cell.get())) {
                    if (Handler.hascurio(living, Items.cell_desecrate.get())) {
                        if (event.getEntity() instanceof Mob mob) {
                            if (!mob.isInvertedHealAndHarm()) {
                                event.setAmount(event.getAmount() * 1.4f);
                            }
                        }
                    }
                    if (Handler.hascurio(living, Items.cell_doctor.get())) {
                        living.heal(event.getAmount() / 5);
                    }
                }
            }
        }
        if (event.getEntity() instanceof Player living) {
            if (Handler.hascurio(living,Items.bloodvirus.get())) {
                if (!living.getCooldowns().isOnCooldown(Items.bat_cell.get())) {

                    if (Handler.hascurio(living, Items.bat_cell.get())) {
                        int j = Mth.nextInt(RandomSource.create(), 1, 7);
                        if (Handler.hascurio(living, Items.cell_harvest.get())) {
                            j = Mth.nextInt(RandomSource.create(), 1, 4);
                        }
                        if (j == 1) {
                            blood_bat blood_bat = new blood_bat(EntityTs.blood_bat.get(), living.level());
                            blood_bat.setOwnerUUID(living.getUUID());
                            blood_bat.teleportTo(living.getX(), living.getY(), living.getZ());
                            if (!Handler.hascurio(living, Items.cell_scientist.get())) {
                                blood_bat.getAttributes().addTransientAttributeModifiers(modifierMultimap(blood_bat, living.getHealth() * 0.15f));
                            } else {
                                blood_bat.getAttributes().addTransientAttributeModifiers(modifierMultimapA(blood_bat, living.getHealth() * 0.25f));
                                living.hurt(living.damageSources().dryOut(), living.getHealth() * 0.3f);
                                living.invulnerableTime = 0;
                            }
                            if (Handler.hascurio(living, Items.cell_doctor.get())) {
                                blood_bat.addTag(cell_doctor);
                            }
                            if (Handler.hascurio(living, Items.cell_desecrate.get())) {
                                blood_bat.addTag(cell_desecrate);
                            }
                            if (Handler.hascurio(living, Items.cell_immortal.get())) {
                                blood_bat.addTag(cell_immortal);
                            }
                            if (Handler.hascurio(living, Items.cell_rage.get())) {
                                blood_bat.addTag(cell_rage);
                            }
                            if (Handler.hascurio(living, Items.cell_blood_attack.get())) {
                                blood_bat.addTag(cell_blood_attack);

                            }
                            if (Handler.hascurio(living, Items.cell_fear.get())) {
                                blood_bat.addTag(cell_fear);
                            }

                            living.level().addFreshEntity(blood_bat);
                            living.getCooldowns().addCooldown(Items.bat_cell.get(), 20);
                            if (Handler.hascurio(living, Items.cell_not_do.get())) {
                                living.hurt(living.damageSources().dryOut(), living.getHealth() * 0.15f);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (Handler.hascurio(slotContext.entity(),Items.cell.get())){
            return false;
        }
        if (Handler.hascurio(slotContext.entity(),Items.giant.get())){
            return false;
        }
        return true;
    }

    public static Multimap<Attribute, AttributeModifier> modifierMultimap (LivingEntity bat, float owner){
        Multimap<Attribute, AttributeModifier> modifierMultimap =HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier((bat.getUUID()), MoonStoneMod.MODID + "bat",owner, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
    public static Multimap<Attribute, AttributeModifier> modifierMultimapA(LivingEntity bat,float owner){
        Multimap<Attribute, AttributeModifier> modifierMultimap =HashMultimap.create();
        modifierMultimap.put(Attributes.ARMOR, new AttributeModifier((bat.getUUID()), MoonStoneMod.MODID + "bat",owner, AttributeModifier.Operation.ADDITION));
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier((bat.getUUID()), MoonStoneMod.MODID + "bat",owner, AttributeModifier.Operation.ADDITION));
        return modifierMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.bat_cell.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.bat_cell.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.bat_cell.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
}
