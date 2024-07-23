package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.necora.red_entity;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.moonstoneitem.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nightmare_cube extends nightmare {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().isShiftKeyDown()) {
            if (slotContext.entity() instanceof Player player) {
                if (!player.getCooldowns().isOnCooldown(this)) {

                    {
                        Vec3 position = slotContext.entity().position();
                        int is = 12;
                        List<LivingEntity> ess = slotContext.entity().level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
                        for (LivingEntity es : ess) {
                            Vec3 motion = position.subtract(es.position().add(0, es.getBbHeight() / 2, 0));
                            if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                motion = motion.normalize();
                            }
                            if (!Handler.hascurio(es, this)) {
                                es.setDeltaMovement(motion.scale(0.125f));
                            }
                        }
                    }
                    Vec3 position = slotContext.entity().position();
                    float is = 0.5f;
                    List<LivingEntity> ess = slotContext.entity().level().getEntitiesOfClass(LivingEntity.class, new AABB(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is));
                    for (LivingEntity es : ess) {
                        if (!Handler.hascurio(es, this)) {

                            if (player.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                                red_entity e = new red_entity(EntityTs.red_entity.get(), player.level());
                                e.setPos(new Vec3(player.getX(), player.getY()-0.25, player.getZ()));
                                e.setNoAi(true);
                                e.setNoGravity(true);
                                e.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 12000, 0, false, false));
                                e.setOwnerUUID(player.getUUID());
                                player.level().addFreshEntity(e);
                                player.getCooldowns().addCooldown(this, 200);

                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_cube.tool.string").withStyle(ChatFormatting.DARK_RED));

    }
}
