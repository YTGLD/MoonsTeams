package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.bolt;
import com.moonstone.moonstonemod.entity.bolt_light;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.MSound;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class light_amout  extends Doom {
    /*
    如果单次坠落距离大于2
    那么接下来的全力一击将附带大范围的闪电攻击

    闪电的伤害会随着自身攻击伤害和摔落距离的增加而增加

    每增加1格，都会增加10%的伤害


     */
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        tooltip.add(Component.translatable("item.light_amout.tool.string").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("item.light_amout.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.light_amout.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.light_amout.tool.string.3").withStyle(ChatFormatting.GOLD));

    }
    public static void Sword_m(LivingHurtEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.light_amout.get())){
                int dist = (int) player.fallDistance;
                if (dist>=2&&player.getAttackStrengthScale(0.5f)==1) {
                    Vec3 playerPos = event.getEntity().position().add(0, 0.75, 0);
                    int range = 4;
                    List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                    for (LivingEntity living : entities) {
                        if (!living.is(player)) {
                            if (!living.is(event.getEntity())) {
                                if (player.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                                    living.hurt(living.damageSources().lightningBolt(),(float) (event.getAmount() + dist + player.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
                                }
                            }
                        }
                    }
                    for (int i = 0; i < 6; i++) {
                        bolt bolt = new bolt(EntityTs.bolt.get(), player.level());
                        bolt.setPos(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                        bolt.setLight(false);
                        bolt.setSize(0.08f);
                        player.level().addFreshEntity(bolt);

                    }

                    if (player.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                        float dam = dist;
                        dam /= 10;
                        event.setAmount(((float) (event.getAmount() + dist + player.getAttribute(Attributes.ATTACK_DAMAGE).getValue()))*(1+dam));
                    }

                    bolt_light bolts = new bolt_light(EntityTs.bolt_light.get(), player.level());
                    bolts.setPos(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                    bolts.setLight(true);

                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.NEUTRAL, 1, 1);
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.NEUTRAL, 1, 1);
                    player.level().addFreshEntity(bolts);
                }
            }
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.getOrCreateTag().putString("a","a");
    }

}
