package com.moonstone.moonstonemod.item.common;

import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.Particles;
import com.moonstone.moonstonemod.item.moonstoneitem.CommonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class flyeye extends CommonItem {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.getOrCreateTag().putBoolean("y",true);

        if (slotContext.entity().invulnerableTime>10){
            slotContext.entity().level() .addParticle(Particles.gold.get(), slotContext.entity().getX(), slotContext.entity().getY()+1, slotContext.entity().getZ(), 0, 0,0);
        }
        if (slotContext.entity().invulnerableTime<=10){
            if (stack.getTag() != null) {
                stack.getTag().putBoolean(AllEvent.FlyEye,false);
            }
        }else {
            if (stack.getOrCreateTag().getBoolean(AllEvent.FlyEye)) {
                Vec3 playerPos = slotContext.entity().position().add(0, 0.75, 0);
                int range = 1;
                List<LivingEntity> entities = slotContext.entity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));



                for (LivingEntity living : entities) {
                    if (!living.is(slotContext.entity())) {

                        living.hurt(living.damageSources().magic(), 10 + slotContext.entity().getHealth() / 10);
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.flyeye.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.flyeye.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.flyeye.tool.string.2").withStyle(ChatFormatting.GOLD));

    }
}
