package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.as_sword;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class as_amout  extends Doom {
    public static void hurt(LivingHurtEvent event){
        if (event.getSource().getDirectEntity() instanceof Player player ){
            if (Handler.hascurio(player, Items.as_amout.get())){
                if (!player.getCooldowns().isOnCooldown(Items.as_amout.get())) {
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT, 2, 2);

                    LivingEntity target = event.getEntity();
                    for (int i = 0; i < 7; i++) {

                        float lvl = Mth.nextFloat(RandomSource.create(), -0.3f, 0.3f);

                        as_sword as_sword = new as_sword(EntityTs.as_sword.get(), player.level());
                        as_sword.setPos(target.position().x, target.position().y + 1, target.position().z);
                        Vec3 forward = player.getLookAngle();
                        double speed = 0.25f;

                        as_sword.setDeltaMovement(forward.add(lvl, lvl, lvl).x * speed, forward.add(lvl, lvl, lvl).y * speed, forward.add(lvl, lvl, lvl).z * speed);
                        as_sword.setOwner(player);

                        player.level().addFreshEntity(as_sword);
                        as_sword.setTarget(target);

                        player.getCooldowns().addCooldown(Items.as_amout.get(),100);
                    }

                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.as_amout.tool.string.1").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.as_amout.tool.string.2").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.as_amout.tool.string.3").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.as_amout.tool.string.4").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.as_amout.tool.string.5").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.translatable("item.as_amout.tool.string.6").withStyle(ChatFormatting.GOLD));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.as_amout.tool.string.7").withStyle(ChatFormatting.GOLD));
    }

}

