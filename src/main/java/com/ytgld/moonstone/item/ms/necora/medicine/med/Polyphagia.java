package com.ytgld.moonstone.item.ms.necora.medicine.med;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.TheNecora;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.List;

public class Polyphagia extends TheNecora  {
    public Polyphagia(Properties properties) {
        super(properties);
    }

    public  static void necora(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.polyphagia.get())){
                if (event.getItem().getUseAnimation() == UseAnim.EAT){
                    player.heal(player.getMaxHealth() / 15);
                }
            }

            if (Handler.hascurio(player, Items.necora.get())) {
                if (event.getItem().is(net.minecraft.world.item.Items.ROTTEN_FLESH)){
                    if (!Handler.hascurio(player, Items.putrefactive.get())) {
                        player.heal(player.getMaxHealth() / 20);
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0));
                    }else {
                        player.heal(player.getMaxHealth() / 15);
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 1));
                    }
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.polyphagia.tool.string").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        if (flags.hasShiftDown()) {
            tooltip.add(Component.translatable("item.polyphagia.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.literal("SHIFT").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
    }

}

