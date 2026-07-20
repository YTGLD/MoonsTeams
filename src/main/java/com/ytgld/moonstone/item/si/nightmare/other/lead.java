package com.ytgld.moonstone.item.si.nightmare.other;

import com.ytgld.moonstone.SIHandler;
import com.ytgld.moonstone.item.Items;
import com.ytgld.moonstone.item.si.nightmare.NightmareSmall;
import com.ytgld.moonstone.other.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

/**

 *
 * 罡风重铅
 * <p>
 * 连续受到伤害时逐渐提高抗性
 * <p>
 * 每次提升3%，但不超过30%
 * <p>
 * <p>
 * 若10秒未受到伤害
 * <p>
 * 则以每秒20点的速度恢复护体罡气
 * <p>
 * 最多恢复至最大生命值的50%
 * <p>
 * <p>
 * 受伤优先损耗罡气而非生命值
 * <p>
 * <p>
 * 护体结界崩碎时对攻击者造成伤害
 *
 *
 *
 */
public class lead  extends NightmareSmall {
    public static final String dieGive ="dieGive";
    public static final String dieGiveBoolean ="dieGiveBoolean";

    public static final String leadHurtSize = "leadHurtSize";


    public static final String gang = "gang";
    public static final String gangBoolean = "gangBoolean";
    public static final String noHurtTime = "noHurtTime";

    public lead(Properties properties) {
        super(properties);
    }


    public static void hurtOfBlood(LivingDamageEvent.Pre event){
        if (!event.getSource().is(DamageTypes.GENERIC_KILL)) {
            if (event.getEntity() instanceof Player player) {
                if (SIHandler.hascurio(player, Items.lead.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.lead.get())) {
                                    CompoundTag compoundTag = stack.get(DataReg.tag);
                                    if (compoundTag != null) {


                                        if (compoundTag.getIntOr(gang,0)>0){
                                            compoundTag.putInt(gang,compoundTag.getIntOr(gang,0)-((int)event.getNewDamage()));
                                            event.setNewDamage(0);
                                        }else if (compoundTag.getBooleanOr(gangBoolean,false)){
                                            if (event.getSource().getEntity() instanceof LivingEntity living) {
                                                living.hurt(living.damageSources().dryOut(),player.getMaxHealth()*0.2f);
                                            }
                                        }

                                        if (compoundTag.getIntOr(leadHurtSize,0) < 10) {
                                            compoundTag.putInt(leadHurtSize,compoundTag.getIntOr(leadHurtSize,0)+1);
                                        }
                                        float s = (compoundTag.getIntOr(leadHurtSize,0)*0.03f);

                                        event.setNewDamage(event.getNewDamage()*(1-s));

                                        compoundTag.putBoolean(gangBoolean,false);
                                        compoundTag.putInt(noHurtTime,0);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void curioTickUse(SlotContext slotContext, ItemStack stack) {
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (slotContext.entity() instanceof Player player) {
            if (compoundTag != null) {
                if (player.tickCount%40==1) {
                    if (!compoundTag.getBooleanOr(gangBoolean,false)) {
                        if (compoundTag.getIntOr(noHurtTime,0) < 10) {
                            compoundTag.putInt(noHurtTime, compoundTag.getIntOr(noHurtTime,0) + 2);
                        }
                    }
                    if (compoundTag.getIntOr(noHurtTime,0) >= 10){
                        if (!compoundTag.getBooleanOr(gangBoolean,false)) {
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 1F, 1F);
                        }
                        compoundTag.putBoolean(gangBoolean,true);
                    }
                    if (compoundTag.getIntOr(leadHurtSize,0) > 0) {
                        compoundTag.putInt(leadHurtSize, compoundTag.getIntOr(leadHurtSize,0) - 1);
                    }
                }

                if (compoundTag.getBooleanOr(gangBoolean,false)) {
                    if (compoundTag.getIntOr(gang,0)<player.getMaxHealth()*0.5f) {
                        compoundTag.putInt(gang, compoundTag.getIntOr(gang,0) + 1);
                    }
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.lead.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.lead.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.lead.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.lead.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.lead.tool.string.5").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.lead.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.lead.tool.string.7").withStyle(ChatFormatting.DARK_RED));
    }
}
