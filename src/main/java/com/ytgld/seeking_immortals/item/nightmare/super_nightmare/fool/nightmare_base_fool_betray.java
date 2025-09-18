package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nightmare_base_fool_betray extends nightmare implements SuperNightmare {

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (Handler.hascurio(slotContext.entity(), this)) {
                if (!player.getCooldowns().isOnCooldown(this)) {
                    Vec3 playerPos = player.position().add(0, 0.75, 0);
                    float range = 10;
                    List<Mob> entities =
                            player.level().getEntitiesOfClass(Mob.class,
                                    new AABB(playerPos.x - range,
                                            playerPos.y - range,
                                            playerPos.z - range,
                                            playerPos.x + range,
                                            playerPos.y + range,
                                            playerPos.z + range));
                    for (Mob mob : entities) {
                        if (mob instanceof OwnableEntity ownableEntity) {
                            if (ownableEntity.getOwner() != null && !ownableEntity.getOwner().is(player)) {
                                ownableEntity.getOwner().hurt(ownableEntity.getOwner().damageSources().dryOut(),
                                        ownableEntity.getOwner().getMaxHealth() / 10);
                            } else {
                                if (mob instanceof TamableAnimal animal) {
                                    for (MobEffectInstance effect : player.getActiveEffects()) {
                                        if (effect != null
                                                && effect.getEffect().isBeneficial()) {
                                            animal.addEffect(effect);
                                        }
                                    }
                                    animal.setOwnerUUID(player.getUUID());
                                }
                            }
                        }


                        if (player.getLastAttacker() != null) {
                            mob.setTarget(player.getLastAttacker());
                        }
                        slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 1, 1);
                        player.getCooldowns().addCooldown(this, 100 + entities.size() * 100);
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_betray.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_betray.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_betray.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_fool_betray.tool.string.3").withStyle(ChatFormatting.DARK_RED));
    }
}


