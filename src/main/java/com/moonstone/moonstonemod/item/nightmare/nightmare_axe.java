package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.axe;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.INightmare;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class nightmare_axe extends nightmare implements INightmare {
    public static void Nig(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmareeye.get())&&Handler.hascurio(player, Items.nightmare_axe.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.nightmare_axe.get())) {
                    axe e = new axe(EntityTs.axe.get(), event.getEntity().level());

                    e.setPos(new Vec3(event.getEntity().getX(), event.getEntity().getY() +15, event.getEntity().getZ()));
                    e.setOwner(player);

                    event.getEntity().level().addFreshEntity(e);

                    player.getCooldowns().addCooldown(Items.nightmare_axe.get(), 100);

                }
            }
        }
    }
    public static void heals(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmareeye.get())&&Handler.hascurio(player, Items.nightmare_axe.get())) {
                Vec3 playerPos = player.position();
                float range =12;
                List<axe> entities =
                        player.level().getEntitiesOfClass(axe.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                float a8 = entities.size();
                a8/=4;
                event.setAmount(event.getAmount()*(1+a8));
            }
        }
    }
    public static void att(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmareeye.get())&&Handler.hascurio(player, Items.nightmare_axe.get())) {
                Vec3 playerPos = player.position();
                float range =12;
                List<axe> entities =
                        player.level().getEntitiesOfClass(axe.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                float a8 = entities.size();
                a8/=4;
                event.setAmount(event.getAmount()*(1+a8));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.nightmare_axe.tool.string").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.translatable("item.nightmare_axe.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.translatable("item.nightmare_axe.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        p_41423_.add(Component.translatable("item.nightmare_axe.tool.string.3").withStyle(ChatFormatting.DARK_RED));
    }

}
