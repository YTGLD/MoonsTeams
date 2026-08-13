package com.ytgld.moonstone.item.ms.ectoplasm;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.event.TextEvt;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.Ectoplasm;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class EctoplasmShild extends Ectoplasm implements TextEvt.Twelve {

    public EctoplasmShild(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 0, false, false));
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag, new CompoundTag());
        }
    }

    public static void hurt(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.ectoplasmshild.get())) {
                if (event.getSource().is(DamageTypes.EXPLOSION)) {
                    event.setNewDamage(event.getNewDamage() * 0.7F);
                }

                player.getPersistentData().putInt("EctoplasmShild", player.getPersistentData().getInt("EctoplasmShild") + 1);
                if (player.getPersistentData().getInt("EctoplasmShild") >= 5) {
                    player.getPersistentData().putInt("EctoplasmShild", 0);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.ectoplasmshild.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.ectoplasmshild.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.ectoplasmshild.tool.string.3").withStyle(ChatFormatting.GOLD));


    }
}
