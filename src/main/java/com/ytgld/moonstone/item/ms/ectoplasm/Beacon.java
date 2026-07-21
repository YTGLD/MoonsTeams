package com.ytgld.moonstone.item.ms.ectoplasm;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.Ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collection;
import java.util.List;

public class Beacon extends Ectoplasm {

    public Beacon(Properties properties) {
        super(properties);
    }

    public static void beacon(LivingDamageEvent.Pre event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.beacon.get())){
                Collection<MobEffectInstance> collection  = player.getActiveEffects();
                if (!collection.isEmpty()) {
                    if (event.getSource().getEntity() != null) {
                        AreaEffectCloud cloud = new AreaEffectCloud(event.getSource().getEntity().level(), event.getSource().getEntity().getX(), event.getSource().getEntity().getY(), event.getSource().getEntity().getZ());
                        cloud.setRadius(2.5F);
                        cloud.setRadiusOnUse(-0.5F);
                        cloud.setWaitTime(10);
                        cloud.setDuration(300);
                        cloud.setPotionDurationScale(0.25F);
                        cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());

                        for(MobEffectInstance mobeffectinstance : collection) {
                            cloud.addEffect(new MobEffectInstance(mobeffectinstance));
                        }
                        event.getSource().getEntity().level().addFreshEntity(cloud);
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