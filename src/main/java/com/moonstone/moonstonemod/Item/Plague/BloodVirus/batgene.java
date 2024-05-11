package com.moonstone.moonstonemod.Item.Plague.BloodVirus;

import com.moonstone.moonstonemod.Item.Plague.BloodVirus.ex.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class batgene extends BloodViru {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            Vec3 playerPos = player.position().add(0, 0.75, 0);
            int range = 24;
            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

            for (LivingEntity living : entities){
                if (living instanceof Bat bat){
                    bat.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100 , 2, false, false));
                    bat.setTarget(player);
                }
            }


        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.batgene.tool.string").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.batgene.tool.string.1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.batgene.tool.string.2").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
