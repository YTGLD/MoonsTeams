package com.ytgld.moonstone.item.ms.ectoplasm;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.Ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Beacon extends Ectoplasm {

    public Beacon(Properties properties) {
        super(properties);
    }

    public static void beacon(LivingDamageEvent.Post event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.beacon.get())){
                Collection<MobEffectInstance> collection  = player.getActiveEffects();
                if (!collection.isEmpty()) {
                    if (event.getSource().getEntity() != null) {
                        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(event.getSource().getEntity().level(), event.getSource().getEntity().getX(), event.getSource().getEntity().getY(), event.getSource().getEntity().getZ());
                        areaeffectcloud.setRadius(2.5F);
                        areaeffectcloud.setRadiusOnUse(-0.5F);
                        areaeffectcloud.setWaitTime(10);
                        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
                        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
                        for(MobEffectInstance mobeffectinstance : collection) {
                            areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
                        }
                        event.getSource().getEntity().level().addFreshEntity(areaeffectcloud);
                        player.removeAllEffects();
                    }
                }
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.beacon.tool.string.1").withStyle(ChatFormatting.GOLD));

    }

}