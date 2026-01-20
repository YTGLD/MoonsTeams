package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.SIHandler;
import com.moonstone.moonstonemod.init.AttReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;


/**
 * 虚伪的自尊
 * <p>
 * 附近每存在一个实体你都会获得下列加强<p>
 * +5%治疗<p>
 * +5%伤害<p>
 * +5%暴击<p>
 * +5%护甲<p>
 * +5%攻速<p>
 * <p>
 * 如果你承受了邪念之窥眸的诅咒..那么：
 * <p>
 * 撕咬附近的生物，每秒造成20%你的面板伤害
 * <p>
 * 每多一个目标，伤害提高20%
 * <p>
 * 每次撕咬都会吸取生命值并对目标造成削弱
 */
public class hypocritical_self_esteem extends nightmare implements SuperNightmare {
    public static final String MALICE_DIE = "HypocriticalSelfEsteem";
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (!player.level().isClientSide) {
                if (SIHandler.hascurio(player,Items.hypocritical_self_esteem.get())) {

                    player.getAttributes().addTransientAttributeModifiers(this.Head(stack));
                    int size = 0;
                    Vec3 playerPos = player.position();
                    int range = 24;
                    List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                    for (LivingEntity living  :entities){
                        if (!living.is(player)&&living.isAlive()) {
                            if (living.tickCount % 30 == 0) {
                                living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.PHANTOM_BITE, SoundSource.AMBIENT, 0.2f, 0.2f);
                                break;
                            }
                        }
                    }

                    for (LivingEntity living : entities) {
                        size = entities.size();

                        if (SIHandler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                            if (!living.is(player)&&living.isAlive()) {
                                if (living.tickCount % 10 == 0) {
                                    int l = entities.size() + 1;
                                    if (l > 10) {
                                        l = 10;
                                    }
                                    living.hurt(living.damageSources().dryOut(),
                                            (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * (0.2f * (l)));

                                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false));
                                    living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, false));
                                    living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 1, false, false));
                                    living.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2, false, false));
                                    living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 3, false, false));
                                    living.invulnerableTime = 0;
                                    player.heal(2);
                                    break;
                                }
                            }
                        }
                    }
                    if (stack.getTag() != null) {
                        stack.getTag().putInt(MALICE_DIE, size);
                    } else {
                        stack.getOrCreateTag();
                    }
                }
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(this.Head(stack));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltip, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string").withStyle(ChatFormatting.DARK_RED));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("effect.minecraft.regeneration").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attribute.name.generic.attack_damage").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attribute.name.generic.armor").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attribute.name.generic.attack_speed").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal("+5%").withStyle(ChatFormatting.RED).append(Component.translatable("attrib.moonstone.critical").withStyle(ChatFormatting.RED)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.1").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDC143C))));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
            tooltip.add(Component.translatable("item.hypocritical_self_esteem.tool.string.4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFff4789))));
        }else {
            tooltip.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
        }

    }

    private  Multimap<Attribute, AttributeModifier> Head(ItemStack stack){
         Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (stack.getTag()!=null) {

            float s = stack.getTag().getInt(MALICE_DIE);//1 == 100%
            if (s > 10) {
                s=10;
            }
            s /= 100f;//0.01 = 1%
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    UUID.fromString("564288ac-221d-47a6-8316-7465979384f2"),"",
                    s*5F,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(AttReg.heal.get(), new AttributeModifier(
                    UUID.fromString("564288ac-221d-47a6-8316-7465979384f2"),"",
                    s*5F,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    UUID.fromString("564288ac-221d-47a6-8316-7465979384f2"),"",
                    s*5F,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(Attributes.ARMOR, new AttributeModifier(
                    UUID.fromString("564288ac-221d-47a6-8316-7465979384f2"),"",
                    s*5F,
                    AttributeModifier.Operation.MULTIPLY_BASE));

            multimap.put(AttReg.cit.get(), new AttributeModifier(
                    UUID.fromString("564288ac-221d-47a6-8316-7465979384f2"),"",
                    s*5F,
                    AttributeModifier.Operation.MULTIPLY_BASE));

        }
        return multimap;
    }



}
