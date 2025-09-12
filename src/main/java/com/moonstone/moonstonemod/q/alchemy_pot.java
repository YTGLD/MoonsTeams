package com.moonstone.moonstonemod.q;

import com.moonstone.moonstonemod.moonstoneitem.INightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class alchemy_pot extends Item implements INightmare, ICurioItem {
    public alchemy_pot() {
        super(new Properties().stacksTo(1).rarity(Rarity.create("nightmare", ChatFormatting.RED)));
    }
    public static final String p1= "PotionItem1";
    public static final String p1Time= "PotionItem1Time";
    public static final String p1Lvl= "PotionItem1Lvl";
    public static final String p2= "PotionItem2";
    public static final String p2Time= "PotionItem2Time";
    public static final String p2Lvl= "PotionItem2vl";
    public static final String p3= "PotionItem3";
    public static final String p3Time= "PotionItem3Time";
    public static final String p3Lvl= "PotionItem3vl";
    public static final String p4= "PotionItem4";
    public static final String p4Time= "PotionItem4Time";
    public static final String p4Lvl= "PotionItem4vl";
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack other, Slot p_150744_, ClickAction p_150745_, Player player, SlotAccess p_150747_) {
        CompoundTag compoundTag =me.getTag();
        if (compoundTag==null){
            me.getOrCreateTag().putBoolean("a",true);
        }

        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(player)) {
            if (other.getItem() instanceof PotionItem) {
                if (compoundTag != null) {
                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p1"))   {
                        compoundTag.putBoolean("111p1", true);
                        addTag(p1, p1Time, p1Lvl, other, compoundTag);
                        player.getCooldowns().addCooldown(this, 10);
                    }
                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p2")) {
                        if (compoundTag.getBoolean("111p1")) {
                            compoundTag.putBoolean("111p2", true);
                            addTag(p2, p2Time, p2Lvl, other, compoundTag);
                            player.getCooldowns().addCooldown(this, 10);
                        }
                    }
                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p3")) {
                        if (compoundTag.getBoolean("111p2")) {
                            compoundTag.putBoolean("111p3", true);
                            addTag(p3, p3Time, p3Lvl, other, compoundTag);
                            player.getCooldowns().addCooldown(this, 10);
                        }
                    }
                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p4")) {
                        if (compoundTag.getBoolean("111p3")) {
                            compoundTag.putBoolean("111p4", true);
                            addTag(p4, p4Time, p4Lvl, other, compoundTag);
                            player.getCooldowns().addCooldown(this, 10);
                        }
                    }


                    other.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }
    public void addTag(String name, String time, String lvl, ItemStack me, CompoundTag compoundTag){
        for (MobEffectInstance mobEffectInstance : PotionUtils.getMobEffects(me)){
            ResourceLocation resourceLocation = BuiltInRegistries.MOB_EFFECT.getKey(mobEffectInstance.getEffect());

            if (resourceLocation!=null) {
                //时间
                compoundTag.putFloat(time, mobEffectInstance.getDuration() + 150);
                compoundTag.putInt(lvl, mobEffectInstance.getAmplifier());
                //药水种类
                compoundTag.putString(name,resourceLocation.toString());
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        CompoundTag compoundTag =stack.getTag();
        if (compoundTag==null){
            stack.getOrCreateTag().putBoolean("a",true);
        }
        if (!slotContext.entity().level().isClientSide&&slotContext.entity().tickCount%20==1) {
            addEffect(compoundTag,slotContext.entity(),p1,p1Time,p1Lvl);
            addEffect(compoundTag,slotContext.entity(),p2,p2Time,p2Lvl);
            addEffect(compoundTag,slotContext.entity(),p3,p3Time,p3Lvl);
            addEffect(compoundTag,slotContext.entity(),p4,p4Time,p4Lvl);
        }
    }
    public void addEffect(CompoundTag compoundTag, LivingEntity entity,String name, String time, String level){
        if (compoundTag != null) {
            String p1Effect = compoundTag.getString(name);

            if (!p1Effect.isEmpty()) {

                String[] pats = p1Effect.split(":");
                ResourceLocation resourceLocation = new ResourceLocation(pats[0], pats[1]);
                MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(resourceLocation);

                float getDuration = compoundTag.getFloat(time);
                if (getDuration > 0) {
                    compoundTag.putFloat(time, getDuration - 20);
                }else {
                    return;
                }

                int lvl = compoundTag.getInt(level);


                if (effect != null) {
                    entity.addEffect(new MobEffectInstance(effect, 100, lvl));
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        CompoundTag compoundTag =p_41421_.getTag();
        if (Screen.hasShiftDown()) {
            if (compoundTag != null) {
                p_41423_.add(Component.literal("effect: " + compoundTag.getString(p1)));
                p_41423_.add(Component.literal("duration: " + compoundTag.getFloat(p1Time)));
                p_41423_.add(Component.literal("lvl: " + compoundTag.getInt(p1Lvl)));
                p_41423_.add(Component.literal(""));
                p_41423_.add(Component.literal("effect: " + compoundTag.getString(p2)));
                p_41423_.add(Component.literal("duration: " + compoundTag.getFloat(p2Time)));
                p_41423_.add(Component.literal("lvl: " + compoundTag.getInt(p2Lvl)));
                p_41423_.add(Component.literal(""));
                p_41423_.add(Component.literal("effect: " + compoundTag.getString(p3)));
                p_41423_.add(Component.literal("duration: " + compoundTag.getFloat(p3Time)));
                p_41423_.add(Component.literal("lvl: " + compoundTag.getInt(p3Lvl)));
                p_41423_.add(Component.literal(""));
                p_41423_.add(Component.literal("effect: " + compoundTag.getString(p4)));
                p_41423_.add(Component.literal("duration: " + compoundTag.getFloat(p4Time)));
                p_41423_.add(Component.literal("lvl: " + compoundTag.getInt(p4Lvl)));
            }
        }else {

        }
    }
}
