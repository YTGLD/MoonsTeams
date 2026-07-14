package com.ytgld.moonstone.item.ms.blood.magic;

import com.ytgld.moonstone.Handler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.ms.BloodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Targeting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UndeadBloodCharm extends BloodItem {

    public UndeadBloodCharm(Properties properties) {
        super(properties);
    }

    public static void LivingIncomingDamageEvent(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.undead_blood_charm.get())) {
                event.setNewDamage(event.getNewDamage() * 1.4f);
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.undead_blood_charm.get())) {
                if (event.getEntity().isInvertedHealAndHarm()) {
                    event.setNewDamage(event.getNewDamage() * 1.5f);
                }
            }
        }
    }

    public static void LivingHealEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.undead_blood_charm.get())) {
                if (event.getAmount() > 0) {
                    event.setAmount(event.getAmount() * 1.5f);


                    double sv = 1;
                    float sl = (float) sv;

                    LivingEntity entity = player.getLastHurtByMob();
                    if (entity != null) {
                        entity.invulnerableTime = 0;
                        entity.hurt(entity.damageSources().dryOut(), event.getAmount() * 1.5f * sl);
                        entity.level().levelEvent(2001, new BlockPos((int) entity.getX(), (int) (entity.getY() + 1), (int) entity.getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                    }


                    Vec3 playerPos = player.position();
                    float range = 8;
                    List<LivingEntity> entities =
                            player.level().getEntitiesOfClass(LivingEntity.class,
                                    new AABB(playerPos.x - range,
                                            playerPos.y - range,
                                            playerPos.z - range,
                                            playerPos.x + range,
                                            playerPos.y + range,
                                            playerPos.z + range));
                    for (LivingEntity living : entities) {
                        if (living instanceof Targeting targeting) {
                            if (targeting.getTarget() != null && targeting.getTarget().is(player)) {
                                living.invulnerableTime = 0;
                                living.hurt(living.damageSources().dryOut(), event.getAmount() * 0.5f * sl);

                                living.level().levelEvent(2001, new BlockPos((int) living.getX(), (int) (living.getY() + 1), (int) living.getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("item.undead_blood_charm.tool.string.1").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.undead_blood_charm.tool.string.2").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.undead_blood_charm.tool.string.3").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.literal(""));
        p_41423_.add(Component.translatable("item.undead_blood_charm.tool.string.4").withStyle(ChatFormatting.RED));
        p_41423_.add(Component.translatable("item.undead_blood_charm.tool.string.5").withStyle(ChatFormatting.RED));
    }


}




