package com.ytgld.seeking_immortals.item.nightmare.base;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
public class lead  extends nightmare implements SuperNightmare, AllTip {
    public static final String dieGive ="dieGive";
    public static final String dieGiveBoolean ="dieGiveBoolean";

    public static final String leadHurtSize = "leadHurtSize";


    public static final String gang = "gang";
    public static final String gangBoolean = "gangBoolean";
    public static final String noHurtTime = "noHurtTime";


    public static void hurtOfBlood(LivingHurtEvent event){
        if (!event.getSource().is(DamageTypes.GENERIC_KILL)) {
            if (event.getEntity() instanceof Player player) {
                if (Handler.hascurio(player,Items.lead.get())) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.lead.get())) {
                                    CompoundTag compoundTag = stack.getTag();
                                    if (compoundTag != null) {


                                        if (compoundTag.getInt(gang)>0){
                                            compoundTag.putInt(gang,compoundTag.getInt(gang)-((int)event.getAmount()));
                                            event.setAmount(0);
                                        }else if (compoundTag.getBoolean(gangBoolean)){
                                            if (event.getSource().getEntity() instanceof LivingEntity living) {
                                                living.hurt(living.damageSources().dryOut(),player.getMaxHealth()*0.2f);
                                            }
                                        }

                                        if (compoundTag.getInt(leadHurtSize) < 10) {
                                            compoundTag.putInt(leadHurtSize,compoundTag.getInt(leadHurtSize)+1);
                                        }
                                        float s = (compoundTag.getInt(leadHurtSize)*0.03f);

                                        event.setAmount(event.getAmount()*(1-s));

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
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        CompoundTag compoundTag = stack.getTag();
        if (slotContext.entity() instanceof Player player) {
            if (compoundTag != null) {
                if (player.tickCount%40==1) {
                    if (!compoundTag.getBoolean(gangBoolean)) {
                        if (compoundTag.getInt(noHurtTime) < 10) {
                            compoundTag.putInt(noHurtTime, compoundTag.getInt(noHurtTime) + 2);
                        }
                    }
                    if (compoundTag.getInt(noHurtTime) >= 10){
                        if (!compoundTag.getBoolean(gangBoolean)) {
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 1F, 1F);
                        }
                        compoundTag.putBoolean(gangBoolean,true);
                    }
                    if (compoundTag.getInt(leadHurtSize) > 0) {
                        compoundTag.putInt(leadHurtSize, compoundTag.getInt(leadHurtSize) - 1);
                    }
                }

                if (compoundTag.getBoolean(gangBoolean)) {
                    if (compoundTag.getInt(gang)<player.getMaxHealth()*0.5f) {
                        compoundTag.putInt(gang, compoundTag.getInt(gang) + 1);
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


    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }
    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"罡星煞曜");
        return map;
    }
    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"罡星煞曜");
        return map;
    }
}
